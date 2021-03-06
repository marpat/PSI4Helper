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

/**
 * Results.java (UTF-8)
 *
 * May 20, 2018
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */

public class Results extends FXMLDocumentController {

    static String removeDuplicates(String s) {
    StringBuilder noDupes = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        String si = s.substring(i, i + 1);
        if (noDupes.indexOf(si) == -1) {
            noDupes.append(si);
        }
    }
    return noDupes.toString();
}
    
    public String results(String psi_call, String psi_method, String psi_func, String psi_cp, String addrunopt, String opt_freq, String resprop) {

// <editor-fold defaultstate="collapsed" desc="Local variables">
        String resultsall0 = "";
        String methfunc = "";
        String aux0 = ""; // reserved for freq optimization
        String aux1 = ""; // goes to call ()
        String aux2 = "";
        String aux3 = "";
      
       if (psi_method.contains("DFT")) {
            methfunc = psi_func;
        } else {
            methfunc = psi_method;
        }       
        if (opt_freq.contains("optfreq")){
            aux0 = "optimize('"+ methfunc +"')\n";
            aux1 = "dertype=1";
        }
        if (psi_cp != null){
        aux2 = "bsse_type = 'cp'";
        } 
        if (resprop.length()>3){
            psi_call = "properties";
            aux3 = "properties = ["+resprop+"]";
            methfunc = "cc2";
        } else {
            aux3 = "";
        }
        if (psi_call.contains("therm")){
        }
        // Formating
        if (addrunopt !=null){
            addrunopt = addrunopt.replaceAll("'", "").replaceAll(" : ", "");
        }
// </editor-fold>
// Get Results, Run
    resultsall0  = aux0
            + "E1, wfn = "
            + psi_call + "('"
            + methfunc + "', "
            + "return_wfn=True, "
            + aux3 + ", "
            + aux1 + ", "
            + aux2 + ", "
            + addrunopt
            + ")\n\n";

   
//resultsall = removeDuplicates(resultsall);
    resultsall0 = resultsall0.replaceAll("(?m),\\s+?,", ",");
    resultsall0 = resultsall0.replaceAll("(?im)((,)\\s,)", "$2");
    resultsall0  = resultsall0.replaceAll("(?im),\\s\\)", ")");
    //Set<String> resultsall = new HashSet<String>(Arrays.asList(resultsall0));
    //log(resultsall0);

    return resultsall0.toString() ;
}

}
