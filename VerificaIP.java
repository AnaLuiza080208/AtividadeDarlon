import java.util.Scanner;

public class VerificaIP {
    final static Scanner LER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Digite o endereço IP (ou 'sair' para encerrar): ");
            String ipDigitado = LER.nextLine();

            if (ipDigitado.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando...");
                break;
            }

            verificarIP(ipDigitado);
            System.out.println();
        }
    }

    public static void verificarIP(String ipDigitado) {
        ipDigitado = ipDigitado.trim().replace(" ", "");

        String[] partes = ipDigitado.split("\\.");
        if (partes.length != 4) {
            System.out.println("IP inválido!");
            return;
        }

        int[] numerosIP = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                numerosIP[i] = Integer.parseInt(partes[i]);
                if (numerosIP[i] < 0 || numerosIP[i] > 255) {
                    System.out.println("IP inválido!");
                    return;
                }
            } catch (Exception e) {
                System.out.println("IP inválido!");
                return;
            }
        }

        int primeiroNumero = numerosIP[0];
        String classe = "";

        if (primeiroNumero >= 1 && primeiroNumero <= 126) {
            classe = "A";
        } else if (primeiroNumero >= 128 && primeiroNumero <= 191) {
            classe = "B";
        } else if (primeiroNumero >= 192 && primeiroNumero <= 223) {
            classe = "C";
        } else if (primeiroNumero >= 224 && primeiroNumero <= 239) {
            classe = "D";
        } else if (primeiroNumero >= 240 && primeiroNumero <= 255) {
            classe = "E";
        } else {
            System.out.println("Classe desconhecida!");
            return;
        }

        if (classe.equals("D") || classe.equals("E")) {
            System.out.println("Classe: " + classe + " (não possui rede e broadcast)");
            return;
        }

        int rede1 = numerosIP[0];
        int rede2 = numerosIP[1];
        int rede3 = numerosIP[2];
        int rede4 = 0;

        int broadcast1 = numerosIP[0];
        int broadcast2 = numerosIP[1];
        int broadcast3 = numerosIP[2];
        int broadcast4 = 255;

        if (classe.equals("A")) {
            rede2 = 0;
            rede3 = 0;
            rede4 = 0;
            broadcast2 = 255;
            broadcast3 = 255;
            broadcast4 = 255;
        } else if (classe.equals("B")) {
            rede3 = 0;
            rede4 = 0;
            broadcast3 = 255;
            broadcast4 = 255;
        } else if (classe.equals("C")) {
            rede4 = 0;
            broadcast4 = 255;
        }

        int inicio4 = rede4 + 1;
        int fim4 = broadcast4 - 1;

        System.out.println();
        System.out.println("classe: " + classe);
        System.out.println("end. rede: " + rede1 + "." + rede2 + "." + rede3 + "." + rede4);
        System.out.println("broadcast: " + broadcast1 + "." + broadcast2 + "." + broadcast3 + "." + broadcast4);
        System.out.println("faixa início: " + rede1 + "." + rede2 + "." + rede3 + "." + inicio4);
        System.out.println("faixa fim: " + broadcast1 + "." + broadcast2 + "." + broadcast3 + "." + fim4);
        System.out.println("-----------------------------------");
    }
}
