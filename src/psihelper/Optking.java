/*
 * Optking.java, part of the PsiHelper project
 * http://chemgplus.blogspot.com/
 * Implementation of PSI4 optking options
 */
package psihelper;

/**
 * Optking.java (UTF-8)
 *
 * Aug 23, 2018
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class Optking
        extends FXMLDocumentController {

    public String optking(String psi_pyapi,
            String king_options) {

// <editor-fold defaultstate="collapsed" desc="Local variables">
        String optkingall
                = "";
        String optkingpart;
        // </editor-fold>

        if (psi_pyapi.contains("YES")) {
        } else {
            king_options
                    = king_options.replaceAll("'",
                            "").
                            replaceAll(":",
                                    "");
        }
        if (king_options.length() == 0) {
            king_options = "";
        } else {
            king_options = king_options.replaceAll(",",
                            "\n");
        }

        optkingpart
                = king_options + ",\n";


        // Cleanup optionpart from duplicates in ArrayList 
        optkingall
                = "\n\nset optking {\n"
                + optkingpart + "\n"
                + "}\n\n";

        optkingall
                = optkingall.replaceAll("(?m)^(null)?,",
                        "");
        optkingall
                = optkingall.replaceAll("(?m)^[ \t]*\r?\n",
                        "");

        return optkingall + "\n";
    }
}
