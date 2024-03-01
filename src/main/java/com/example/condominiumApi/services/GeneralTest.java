package com.example.condominiumApi.services;

import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class GeneralTest {

    public String opa () {
        System.out.println("Opa");
        return "Opa";
    }

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
}
