package com.example.condominiumApi.services;

import com.example.condominiumApi.dtos.response.FixPositionVariableResponseDto;
import com.example.condominiumApi.dtos.result.FixPositionVariableResultDto;
import com.example.condominiumApi.services.variables.FixPositionVariableService;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Service
public class CalculateService {

    @Autowired
    private FixPositionVariableService fixPositionVariableService;

    private static Double evaluateExpression(String expression, int var1, int var2, int var4) {
        // Substituir placeholders pelas variáveis reais
        expression = expression.replaceAll("var1", Integer.toString(var1));
        expression = expression.replaceAll("var2", Integer.toString(var2));
        expression = expression.replaceAll("var4", Integer.toString(var4));

        // Avaliar a expressão usando MVEL
        return MVEL.eval(expression, Double.class);
    }

    public Double calculation() {
        // Variáveis existentes
        int var1 = 10;
        int var2 = 20;
        int var4 = 130;

        // Expressão definida pelo usuário
        String expression = "(var1 < 5 && var2 < 25) ? (var1 + var2) : ((var1 == 50) ? (var2 - var1) : (var1 * var2)/ var4)";

        // Interpretar e calcular o valor da terceira variável
        Double var3 = evaluateExpression(expression, var1, var2, var4);

        // Formatar o resultado para duas casas decimais
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
        var3 = Double.valueOf(df.format(var3));

        // Imprimir resultado
        System.out.println("O valor de var3 é: " + var3);

        return var3;
    }

////////////////////////////////////////////////////////////////////////////////////////////////

    private static Double evaluate(FixPositionVariableResponseDto variable, List<FixPositionVariableResponseDto> variablesList) {
        String expression = variable.value();
        for (FixPositionVariableResponseDto variableResult : variablesList) {
            expression = expression.replaceAll(variableResult.name(), variableResult.value());
        }
        // Avaliar a expressão usando MVEL
        return MVEL.eval(expression, Double.class);
    }

    public FixPositionVariableResultDto calculateResult(){
        FixPositionVariableResultDto result = new FixPositionVariableResultDto();

        List<FixPositionVariableResponseDto> variablesList = fixPositionVariableService.findAll();

        for (int i = 0; i < variablesList.size(); i++) {
            FixPositionVariableResponseDto variable = variablesList.get(i);
            if ("Conditionals".equals(variable.typeVariable())) {
                Double expressionResult = evaluate(variable, variablesList);
                // Criar uma nova instância do record com o valor atualizado
                FixPositionVariableResponseDto updatedVariable = new FixPositionVariableResponseDto(
                        variable.id(),
                        variable.name(),
                        variable.typeVariable(),
                        String.valueOf(expressionResult),
                        variable.executionOrder()
                );
                // Substituir a instância existente na lista pela nova instância
                variablesList.set(i, updatedVariable);
            }
        }

        // Ao final do loop, o resultado estará na última variável atualizada
        result.setValue(variablesList.get(variablesList.size() - 1).value());

        return result;
    }

}
