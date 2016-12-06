package kz.allpay.mfs.webshop;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kz.allpay.mfs.webshop.keys.KeyGenerator;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author magzhan.karasayev
 * @since 4/18/16 3:24 PM
 */
public class Main {
    @Parameter(names = {"--help", "-h"}, help = true, description = "Show this help")
    private boolean help = false;

    @Parameter(names = {"-p", "--path"}, description = "Full path where generated key files will be saved")
    private String pathForKeysToSave = Paths.get("").toAbsolutePath().normalize().toString();

    @Parameter(names = {"-n", "--name"},
            description = "You company name. This parameter will be used for key file names as prefix",
            required = true)
    private String namePrefix;


    public static void main(final String[] args) {
        // because JCommander asks object in constructor
        final Main main = new Main();
        main.doMain(args);
    }

    private void doMain(final String[] args) {
        final JCommander jc = new JCommander(this);
        jc.setProgramName("KeyGenerator");

        try {
            jc.parse(args);
        } catch (ParameterException pe) {
            System.err.println(pe.getMessage());
            jc.usage();
            return;
        }

        if (help) {
            jc.usage();
            return;
        }

        try {
            final KeyGenerator keyGenerator = new KeyGenerator();
            keyGenerator.generateAndSave(pathForKeysToSave, namePrefix);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(
                    "For some reason key file saving failed.\n" +
                            "Please check that specified path exists\n" +
                            "and all permissions are set");
        }
    }
}
