/*
 * The MIT License
 *
 * Copyright 2018 Marcel Patek <chemgplus at gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package psihelper;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Options.java (UTF-8)
 *
 * May 12, 2018
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class Options extends FXMLDocumentController {

//    private Class<? extends Options> Classcls;
//    public String options(String... args)
// below is verbose alternative
    public String options(
            String psi_pyapi,
            String psi_freeze,
            String psi_bas,
            String psi_ref,
            String psi_scftype,
            String psi_puream,
            String psi_natorb,
            String psi_print,
            String psi_prmos,
            String psi_prbasis,
            String opt_type,
            String set_alone,
            String addoptions,
            String psi_solvent) {

// <editor-fold defaultstate="collapsed" desc="here your description">        
//         // Creating object of a class   
//        Options calculate = new Options();  
//        // instantiating Class class  
//        Classcls = calculate.getClass();  
//        // Getting declared methods inside the Calculate class  
//        Method[] method = Classcls.getDeclaredMethods(); // It returns array of methods
//        for (Method method2 : method) {  
//            System.out.print(method2.getName()); 
//        // Getting parameters of each method  
//            Parameter parameter[] = method2.getParameters(); // It returns array of parameters  
//            // Iterating parameter array  
//            for (Parameter parameter2 : parameter) {  
//                System.out.print(""+parameter2.getParameterizedType()+"\n"); // returns type of parameter  
//                System.out.print(""+parameter2.getName()+"\n"); // returns parameter name  
//            }  
//            System.out.println();  
//        
//        }
//        for (String arg: args) { 
//                    log(args);
//    }
// </editor-fold>    
// <editor-fold defaultstate="collapsed" desc="Local variables">
//Local variables 
        String optionall = "";
        String set_main;
        String linkR;
        String linkT;
        String link3;
        String pcm = "";
        String pcmtrue = "";
        // </editor-fold>

        //PSI4 opt = new PSI4();
        
//// <editor-fold defaultstate="collapsed" desc="Set extra options">
        if (addoptions.length() == 0) {
            set_main = "";
        } else {
            addoptions = addoptions.replaceAll(",", "\n");
            set_main = addoptions; //opt.SetOptions.getText();
        }
        
        if (psi_pyapi.contains("YES")) {
        } else {
            psi_bas = psi_bas.replaceAll("'", "").replaceAll(":", "");
            psi_ref = psi_ref.replaceAll("'", "").replaceAll(":", "");
            psi_scftype = psi_scftype.replaceAll("'", "").replaceAll(":", "");
            psi_freeze = psi_freeze.replaceAll("'", "").replaceAll(":", "");
            psi_puream = psi_puream.replaceAll("'", "").replaceAll(":", "");
            psi_natorb = psi_natorb.replaceAll("'", "").replaceAll(":", "");
            psi_print = psi_print.replaceAll("'", "").replaceAll(":", "");
            psi_prmos = psi_prmos.replaceAll("'", "").replaceAll(":", "");
            psi_prbasis = psi_prbasis.replaceAll("'", "").replaceAll(":", "");
            opt_type = opt_type.replaceAll("'", "").replaceAll(":", "");
            set_alone = set_alone.replaceAll("'", "").replaceAll(":", "");
            set_main = set_main.replaceAll("'", "").replaceAll(":", "");
        }
//// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="psi_solvent">

if (psi_solvent.length() > 0){
    pcmtrue = "pcm true \n"
            + "pcm_scf_type total\n";
}

pcm = "pcm = {\n"
    + "Units = Angstrom\n"
    + "Medium {\n"
    + "   SolverType = IEFPCM\n"
    + "   Solvent = "+ psi_solvent +"\n"
    + "       }\n"
    +"Cavity {\n"
    +"    RadiiSet = UFF\n"
    +"    Type = GePol\n"
    +"    Scaling = False\n"
    +"    Area = 0.3\n"
    +"    Mode = Implicit\n"
    +"       }\n"
    +"}\n";
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Checkboxes 2 [set_main]">
//        if (opt.PsiFreeze.isSelected()) {
//            psi_freeze = "'freeze_core': 'true'";
//        }
//        log(psi_freeze);
// </editor-fold>
        optionall = "\n\nset {\n"
                + psi_bas + ",\n"
                + psi_ref + ",\n"
                + psi_scftype + ",\n"
                + psi_freeze + ",\n"
                + psi_puream + ",\n"
                + psi_natorb + ",\n"
                + psi_print + ",\n"
                + psi_prmos + ",\n"
                + psi_prbasis + ",\n"
                + opt_type + ",\n"
                + set_alone + ",\n"
                + pcmtrue + "\n"
                + set_main + "\n"
                + "}\n\n";
       
        optionall = optionall.replaceAll("(?m)^(null)?,", "");
        optionall = optionall.replaceAll("(?m)^[ \t]*\r?\n", "");
        
        if (psi_solvent.length()>0){
            optionall = optionall + "\n" + pcm;
        }

        if (psi_pyapi.contains("YES")) {
        } else {
            optionall = optionall.replaceAll(",", "");
        }
        
        return optionall + "\n";
    }
}
