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
 * Geo.java (UTF-8)
 *
 * May 5, 2018
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
package psihelper;

public class Geo extends FXMLDocumentController {

    /**
     * *
     *
     * @param molname filename
     * @param charge
     * @param multi
     * @param psi_geom composite of coordinates or PubChem string and possibly
     * no_orient, no_com keywords
     * @param ingeo1 no_orient
     * @param ingeo2 no_com
     * @param psi_point symmetry group
     * @param psi_solvent
     * @param psi_sapt
     * @return geoall string
     */
    public static String Geosection(
            String molname,
            String charge,
            String multi,
            String psi_geom,
            String write47,
            String ingeo1,
            String ingeo2,
            String psi_point,
            String psi_solvent,
            String psi_sapt
            ) {
        /**
         *
         * the whole geometry section in {...} All inherited variables:
         * file_name, suff, inp_dir, memory, cores, molname, psi_method,
         * psi_bas, write47, psi_funct, opt_type, set_options, psi_molcomment,
         * psi_charge, psi_multi, psi_geom, psi_pubchem, psi_run, set_main,
         * set_alone, link2, ingeo1, ingeo2, psi_point
         */

        // local variables
        String molxyz = "";
        String geoall;
        String readxyz = "";
        String nocom;
        String noorient;
        String symm = "";
        String set_symm = "";

        //Conditions
        // Geometry orientation and movement seetings
        if ("YES".equals(psi_sapt)){
           ingeo1 = "\nno_com\n";
           ingeo2 = "\nno_reorient\n";
           psi_point = "c1";
        }
        if ("YES".equals(write47)){
           ingeo1 = "\nno_com\n";
           ingeo2 = "\nno_reorient\n";
        }
        
        if (ingeo1.length() > 1) {
            nocom = "True";
        }
        else {
            nocom = "False";
        }
        if (ingeo2.length() > 1) {
            noorient = "True";
        }
        else {
            noorient = "False";
        }
        if (psi_solvent.length() > 0 && psi_point.length() <1){
            psi_point = "c1";
        }
        if (psi_point.length() > 0) {
            set_symm = "   set_point_group(" + psi_point + ")";
        } else {
            symm = "";
        }

        
        if (psi_geom.contains(".xyz")) {
            molxyz = "has xyz extension";
            readxyz = "# Reading in .xyz file\n"
                    + "with open('" + "./" + molname + ".xyz', 'r') as myfile:\n"
                    + "  fin = myfile.read()\n\n"
                    + "fixcom = not "+ nocom +"\n"
                    + "fixorient = not "+ noorient +"\n"
                    + "qmol = qcdb.Molecule.from_string(fin, dtype='xyz+', fix_com = fixcom, fix_orientation = fixorient)\n"
                    + "" + molname + " = geometry(qmol.create_psi4_string_from_molecule())\n"
                    + "" + molname + ".update_geometry()\n\n"
                    + "" + molname + ".set_molecular_charge(" + charge + ")\n"
                    + "" + molname + ".set_multiplicity(" + multi + ")\n"
                    + set_symm;

        }
        
                if (psi_geom.contains(".mol2")) {        
            molxyz = "has mol2 extension";
            readxyz = "\nimport subprocess, sys, os, time\n"
                    + "\n# Make sure that OpenBabel is installed in /usr/local/bin/ or it is in the PATH.\n"
                    + "try:\n"
                    + "    cmd = 'obabel -imol2 " + molname + ".mol2 -oxyz -O " + molname + "_inp.xyz -j -c\'\n"
                    + "    p = subprocess.Popen(cmd, shell=True, stderr=subprocess.PIPE)\n"
                    + "    print(\"Sybyl .mol2 file was read in.\")\n\n"
                    + "except Exception as e:\n"
                    + "    print(\"Type error when creating xyz file: \" + str(e))\n"
                    + "    pass\n\n" 
                    + "# Reading in .mol2 file using Babel\n"
                    + "sleep(5)\n"
                    + "print(\"Conversion to .xyz format was done, continuing ...\")"
                    + "with open('" + "./" + molname + "_inp.xyz', 'r') as myfile:\n"
                    + "  fin = myfile.read()\n\n"
                    + "fixcom = not "+ nocom +"\n"
                    + "fixorient = not "+ noorient +"\n"
                    + "qmol = qcdb.Molecule.from_string(fin, dtype='xyz+', fix_com = fixcom, fix_orientation = fixorient)\n"
                    + "" + molname + " = geometry(qmol.create_psi4_string_from_molecule())\n"
                    + "" + molname + ".update_geometry()\n\n"
                    + "" + molname + ".set_molecular_charge(" + charge + ")\n"
                    + "" + molname + ".set_multiplicity(" + multi + ")\n"
                    + set_symm;

        }

        if (psi_point.length() > 0) {
            symm = "symmetry " + psi_point + "\n";
        } else {
            symm = "";
        }

        if (molxyz.length() < 1) {
            // Building mol geometry skeleton
            String nameit = "molecule " + molname + " {" + "\n";
            String chgmult = charge + " " + multi + "\n";
            String geo = psi_geom + "\n"; // psi_geom
            nocom = ingeo1 + "\n";
            noorient = ingeo2 + "\n";
            String geo_lower = "\n"
                    + "}\n";
            geoall = nameit + chgmult + geo + nocom + noorient + symm + geo_lower;
            geoall = geoall.replaceAll("(?m)^[ \t]*\r?\n", "");
        } else {
            geoall = readxyz;
        }

        return geoall+"\n";
    }
}
