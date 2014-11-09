/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Mario
 */
public class Analizador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        // TODO code application logic here
        UpdComp scanner;
        if (args.length != 1) {
            System.out.println("Usage: scanner [options] [path]");
            return;
        }
        if (args[0].equals("-i")) {
            scanner = new UpdComp(System.in);
        } else {
            String path_to_tokens[] = args[0].split("\\.");
            if (path_to_tokens[path_to_tokens.length - 1].equals("upd")) {
                scanner = new UpdComp(new FileInputStream(args[0]));
            } else {
                System.out.println("extension de archivo no reconocida");
                return;
            }
        }
        parser parser = new parser(scanner);
        parser.parse();
    }

}
