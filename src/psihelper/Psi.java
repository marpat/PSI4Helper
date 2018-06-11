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
/**
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
package psihelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rubidium
 */
public class Psi extends FXMLDocumentController {

    /**
     * *
     * This module organized variables and creates the final input (sh) files
     * use to start the PSI4 calculations. Variables are coming from the Main
     * PSI$ module and are sent out to other modules for specific tasks
     * (geometry, options)
     *
     * @param psi_pyapi
     * @param file_name
     * @param suff
     * @param ingeo1
     * @param memory
     * @param ingeo2
     * @param molname
     * @param inp_dir
     * @param psi_bas
     * @param cores
     * @param psi_funct
     * @param psi_method
     * @param psi_pubchem
     * @param opt_type
     * @param psi_call
     * @param psi_point
     * @param psi_molcomment
     * @param psi_geom
     * @param psi_multi
     * @param set_alone
     * @param psi_freeze
     * @param psi_ref
     * @param psi_scftype
     * @param psi_puream
     * @param psi_natorb
     * @param psi_prmos
     * @param psi_prbasis
     * @param psi_moldenout
     * @param psi_fchkout
     * @param psi_gdma
     * @param psi_xyz
     * @param addoptions
     * @param num_cube
     * @param CubeProp
     * @param psi_print
     * @param psi_charge
     * @param link2
     * @param psi_prop
     * @param psi_solvent
     * @param psi_local
     * @param mwfn_path
     * @param write47
     * @param writewfx
     * @param psi_sapt
     * @return
     * @throws java.io.IOException
     */
    @SuppressWarnings("UseSpecificCatch")
    // <editor-fold defaultstate="collapsed" desc="Inputa">
    public boolean Inputa(
            String psi_pyapi,
            String file_name,
            String suff,
            String inp_dir,
            String memory,
            String cores,
            String molname,
            String psi_method,
            String psi_funct,
            String psi_point,
            String opt_type,
            String psi_molcomment,
            String psi_charge,
            String psi_multi,
            String psi_geom,
            String psi_pubchem,
            String psi_call,
            String set_alone,
            String link2,
            String ingeo1,
            String ingeo2,
            String psi_freeze,
            String psi_bas,
            String psi_ref,
            String psi_scftype,
            String psi_puream,
            String psi_natorb,
            String psi_print,
            String psi_prmos,
            String psi_prbasis,
            String psi_moldenout,
            String psi_fchkout,
            String psi_gdma,
            String psi_xyz,
            String addoptions,
            String addrunopt,
            String num_cube,
            String CubeProp,
            String psi_prop,
            String psi_solvent,
            String psi_local,
            String mwfn_path,
            String write47,
            String writewfx,
            String psi_sapt,
            String psi_cp,
            String psi_ther,
            String set_univ,
            String opt_freq,
            String resprop,
            String psi_irc) throws IOException // </editor-fold>
    {

// <editor-fold defaultstate="collapsed" desc="Variables">
        String file_ext = "inp";
        String input;
        String skeleton;
        String geo;  // geometry section which include coordinates and keywords
        String opt;
        String run;
        String resout;
        String mwfnsh;
        String geo_get; // aggregate field for any source of geometry
        String addlink; // wfn
        String addlink2; // nbo
        String skeleton_enh = "";
        String skeleton_enh1 = "";
        String method_choose;
        String batch;
        String proc = "";
        String sh47;
        Boolean Wwfx = false;
        Boolean W47 = false;
        String setopt;
        String setopt1;
// </editor-fold>

        if (psi_pyapi.contains("YES")) {
            proc = "set_num_threads " + cores + "\n\n";
        } else {
            proc = "";
        }
        if (writewfx != null) {
            Wwfx = true;
        }
        if (write47 != null) {
            W47 = true;
        }

// <editor-fold defaultstate="collapsed" desc="unused">
//        if (psi_geom.length() == 0) {
//            geo_get = "<Geometry goes here. Leave one empty line before $NBO line below.>\n";
//        }
//        if (psi_pubchem.length() > 1) { // opt and SP linked
//            geo_get = psi_pubchem;
//        } else {
//            geo_get = psi_geom;
//        }
        // Clean the route card from multiple spaces
//        String card = "# " + psi_method + " " + optmethod + " " + moreparam + " " + pop + " nosymm\n";
//        try {
//           card = card.replaceAll("(?m)([aA-zZ])(\\s{2,6})([aA-zZ])", "$1 $3");
//        } catch (PatternSyntaxException ex) {
//            // Syntax error in the regular expression
//        } catch (IllegalArgumentException ex) {
//            // Syntax error in the replacement text (unescaped $ signs?)
//        } catch (IndexOutOfBoundsException ex) {
//            // Non-existent backreference used the replacement text
//        }
//     String name, String charge, String multi, String molecule
// </editor-fold>
        skeleton = "#! " + psi_molcomment + "\n\n"
                + "memory " + memory + " GB\n"
                + proc
                + "import numpy as np" + "\n"
                + "\n";
        geo = Geo.Geosection(molname, psi_charge, psi_multi, psi_geom, ingeo1, ingeo2, psi_point, psi_solvent, psi_sapt);

        // Options section. Create an object first.
        Options geo_main = new Options();
        opt = geo_main.options(psi_pyapi, psi_freeze, psi_bas, psi_ref, psi_scftype, psi_puream, psi_natorb, psi_print, psi_prmos, psi_prbasis, opt_type, set_alone, addoptions, psi_solvent, psi_sapt, set_univ, resprop,psi_irc);

        Results calls = new Results();
        run = calls.results(psi_call, psi_method, psi_funct, psi_geom, psi_cp, addrunopt, opt_freq, resprop);

        Outputs outp = new Outputs();
        resout = outp.outputs(inp_dir, psi_moldenout, psi_fchkout, psi_gdma, psi_xyz, molname, num_cube, CubeProp, psi_prop, psi_local, psi_ther);

        if (psi_irc  != null) {
            setopt = "set " + psi_bas + "\n"
                    + "set 'hessian_write' : 'true'\n"
                    + "\n\nhessian('"+psi_method+"', dertype=1)\n\n";
            setopt1 = "set g_convergence gau_verytight\n\n";
            run = "energy = optimize('"+psi_method+"')\n";
            setopt = setopt.replaceAll("'", "").replaceAll(":", "");
            
            input = skeleton + geo + setopt + geo + setopt1 + opt + run + resout;
            //input = "ha";
        } else {
            input = skeleton + geo + opt + run + resout;
            //input="else";
        }

// <editor-fold defaultstate="collapsed" desc="mwfn sh">
        mwfnsh = "#!/bin/bash\n"
                + "VERSION=1.0\n\n"
                + "# Usage:\n"
                + "# multiwfn.sh, expects hard-coded values for [f, comp_dir, mwfn_dir]\n\n"
                + "function version_page {\n"
                + "echo \"$(basename \"$0\")\" \"version:\" \"$VERSION\"\n"
                + "}\n"
                + "version_page\n\n"
                + "comp_dir='" + inp_dir + "'\n"
                + "mwfn_dir='" + mwfn_path + "'\n" //'/home/mp/Computation/Multiwfn_3.5_bin_Linux'
                + "f='" + file_name + suff + "'\n\n"
                + "mwfn() {\n"
                + "   if hash $mwfn_dir/multiwfn 2>/dev/null; then\n"
                + "      echo 'Multiwfn was found at $mwfn_dir'\n"
                + "      echo ''\n"
                + "   else\n"
                + "      echo 'Multiwfn was not found'\n"
                + "      echo ''\n"
                + "      EXIT\n"
                + "   fi\n"
                + "     }\n"
                + "mwfn\n"
                + "# write customized file for Multiwfn inputs\n"
                + "cat >${mwfn_dir}/wfx1.txt <<EOL\n"
                + "100\n"
                + "2\n"
                + "4\n"
                + "${f}_mwfn.wfx\n"
                + "EOL\n\n"
                + "echo 'Instructions for the Multiwfn run are:'\n"
                + "cat ${mwfn_dir}/wfx1.txt\n\n"
                + "cd $comp_dir\n\n"
                + "# copy molden file to Multiwfn directory\n"
                + "cp ${comp_dir}/${f}.molden ${mwfn_dir}/${f}.molden\n\n"
                + "# Run the binary and create wfx file\n"
                + "echo \'running Multiwfn:\'\n"
                + "cd $mwfn_dir\n"
                + "/bin/bash -c \'exec Multiwfn \'$f\'.molden < wfx1.txt > test.out\'\n\n"
                + "# copy wfx file back to original directory\n"
                + "rm ${mwfn_dir}/${f}.molden\n"
                + "mv ${mwfn_dir}/${f}_mwfn.wfx ${comp_dir}/${f}_mwfn.wfx\n"
                + "echo \'DONE. Files were moved to $comp_dir\'";

        sh47 = "#!/bin/bash\n"
                + "VERSION=1.0\n\n"
                + "# Usage:\n"
                + "# sh47.sh, expects hard-coded values for [f, comp_dir, mwfn_dir]\n\n"
                + "function version_page {\n"
                + "echo \"$(basename \"$0\")\" \"version:\" \"$VERSION\"\n"
                + "}\n"
                + "version_page\n\n"
                + "comp_dir='" + inp_dir + "'\n"
                + "mwfn_dir='" + mwfn_path + "'\n" //'/home/mp/Computation/Multiwfn_3.5_bin_Linux'
                + "f='" + file_name + suff + "'\n\n"
                + "mwfn() {\n"
                + "   if hash $mwfn_dir/multiwfn 2>/dev/null; then\n"
                + "      echo 'Multiwfn was found at $mwfn_dir'\n"
                + "      echo ''\n"
                + "   else\n"
                + "      echo 'Multiwfn was not found'\n"
                + "      echo ''\n"
                + "      EXIT\n"
                + "   fi\n"
                + "     }\n"
                + "mwfn\n"
                + "# write customized file for Multiwfn inputs\n"
                + "cat >${mwfn_dir}/wfx2.txt <<EOL\n"
                + "100\n"
                + "2\n"
                + "8\n"
                + "${f}_mwfn.47\n"
                + "EOL\n\n"
                + "echo 'Instructions for the Multiwfn run are:'\n"
                + "cat ${mwfn_dir}/wfx2.txt\n\n"
                + "cd $comp_dir\n\n"
                + "# copy molden file to Multiwfn directory\n"
                + "cp ${comp_dir}/${f}.molden ${mwfn_dir}/${f}.molden\n\n"
                + "# Run the binary and create .47 file\n"
                + "echo \'running Multiwfn:\'\n"
                + "cd $mwfn_dir\n"
                + "/bin/bash -c \'exec Multiwfn \'$f\'.molden < wfx2.txt > test.out\'\n\n"
                + "# copy wfx file back to original directory\n"
                + "rm ${mwfn_dir}/${f}.molden\n"
                + "mv ${mwfn_dir}/${f}_mwfn.47 ${comp_dir}/${f}_mwfn.47\n"
                + "echo \'DONE. Files were moved to $comp_dir\'";
// </editor-fold>

//        if (link1 == true || linkx == true) { // wfn link1
//            skeleton_enh1 = addlink;
//        } else {
//            skeleton_enh1 = "";
//        }
//
//        if (link2.length() > 1) { //nbo link1 for NBO
//            skeleton_enh = skeleton + addlink2;
//        } else {
//            skeleton_enh = skeleton;
//        }
// <editor-fold defaultstate="collapsed" desc="write files">
        try {
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter(inp_dir + File.separator + file_name + suff + "." + file_ext);
            try (BufferedWriter out = new BufferedWriter(fstreamWrite)) {
                out.write(input);
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        if (true == Wwfx) {
            try {
                // write bash file in the same directory
                FileWriter fstreamWrite = new FileWriter(inp_dir + "/multiwfn.sh");
                BufferedWriter out = new BufferedWriter(fstreamWrite);
                out.write(mwfnsh);
                out.close();
                //Close the input stream

            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
        if (W47) {
            try {
                // write bash file in the same directory
                FileWriter fstreamWrite = new FileWriter(inp_dir + "/sh47.sh");
                BufferedWriter out = new BufferedWriter(fstreamWrite);
                out.write(sh47);
                out.close();
                //Close the input stream

            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
// </editor-fold>

        //appendToPane(AreaOut, input, new Color(0, 128, 0), true);
        boolean confirm = FileExists.Confirm(inp_dir, file_name, suff, file_ext);
        return confirm;
    }

//    public boolean Conf (String inp_dir, String file_ext ){
//       boolean confirm = FileExists.Confirm(inp_dir, file_name, file_ext);
//       return confirm; 
//    }
    // appendToPane(AreaOut, input, new Color(128, 0, 0), false);   
}
