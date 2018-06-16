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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Outputs.java (UTF-8)
 *
 * May 20, 2018 "
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class Outputs extends FXMLDocumentController {

    private static void copyFile(File source,
            File dest) throws IOException {
        if (Files.notExists(dest.toPath())) {
            Files.copy(source.toPath(), dest.toPath());
        }
    }

    public String outputs(
            String inp_dir,
            String psi_moldenout,
            String psi_fchkout,
            String psi_gdma,
            String psi_xyz,
            String molname,
            String num_cube,
            String CubeProp,
            String psi_prop,
            String psi_local,
            String psi_ther) throws IOException {

        String outputsall = "";
        String xyzout = "";
        String savexyz;
        String cubes = "";
        String cubeorb;
        String psiprop = "";
        String psiloc = "";
        String moloc;
        String movecube;
        String cubemove;
        String countorb;

        // Copy vmd_cube.py file into ./cubes directory
//        String pat="";
//        try {
//            pat = new File(PSI4.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(Outputs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        File source1 = new File(pat + File.separator + "vmd_cube.py");
//        File dest1 = new File(inp_dir + File.separator + "cubes/vmd_cube.py");
//        AreaOut.setText("File copied to: " + dest1);
//        //log(source1);
//        //log(dest1);
//        copyFile(source1, dest1);
        //log("CubeProp:" + CubeProp);
        //log("num_cube:" + num_cube);
        inp_dir = inp_dir.replace("\\", "/"); // for all platforms

        cubemove = "import os\n"
                + "from pathlib import Path\n"
                + "import shutil\n\n"
                + "now = str(Path().absolute())\n"
                + "if not os.path.exists(now+'/cubes'):\n"
                + "    os.makedirs(now+'/cubes')\n"
                + "moveto = now + '/cubes/'\n"
                + "files = []\n"
                + "for filename in os.listdir():\n"
                + "    if filename.endswith('.cube'):\n"
                + "        files.append(filename)\n"
                + "for f in files:\n"
                + "    try:\n"
                + "        src = now+'/'+f\n"
                + "        dst = moveto+f\n"
                + "        shutil.move(src,dst)\n"
                + "    except Exception as e:\n"
                + "        print(\"Type error: \" + str(e))\n"
                + "        pass\n"
                + "print('Moved {} files to ./cubes directory.'.format(len(files)))\n"
                + "#os.system('python ' + now+'/cubes/vmd_cube.py')";

        countorb = "nocca = wfn.nalpha()\n"
                + "noccb = wfn.nbeta()\n"
                + "ndocc = min(nocca, noccb)\n"
                + "nocc = max(nocca, noccb)\n"
                + "nsocc = nocc - ndocc\n"
                + "\n"
                + "mints = psi4.core.MintsHelper(wfn.basisset())\n"
                + "S = np.asarray(mints.ao_overlap())\n"
                + "nbf = S.shape[0]\n"
                + "\n"
                + "HOMO = np.asarray(wfn.epsilon_a_subset(\"AO\", \"ALL\"))[wfn.nalpha()]\n" 
                +"LUMO = np.asarray(wfn.epsilon_a_subset(\"AO\", \"ALL\"))[wfn.nalpha() + 1]\n" 
                + "\n"
                + "print('The HOMO - LUMO gap is: %16.8f hartree' % (LUMO - HOMO))\n"
                + "\n"
                + "print('Number of doubly occupied orbitals:   %d' % ndocc)\n"
                + "print('Number of singly occupied orbitals:   %d' % nsocc)\n"
                + "print('Number of basis functions:            %d' % nbf)\n";

        //work on num_cube string
        if (num_cube.toLowerCase().contains("HOMO".toLowerCase())) {
            num_cube = num_cube.toLowerCase().replace("HOMO".toLowerCase(), "$ndocc");
        }
        if (num_cube.toLowerCase().contains("LUMO".toLowerCase())) {
            num_cube = num_cube.toLowerCase().replace("LUMO".toLowerCase(), "$ndocc+1");
        }
        if (num_cube.toLowerCase().contains("SOMO".toLowerCase())) {
            num_cube = num_cube.toLowerCase().replace("SOMO".toLowerCase(), "$nsocc");
        }

        if (num_cube.length() < 1) {
            cubeorb = "";
            countorb = "";
        } else {
            cubeorb = "set cubeprop_orbitals [" + num_cube + "]";
            //copyFile(source1, dest1);
        }
        if (CubeProp.length() > 0) {
            cubes = countorb + "\n"
                    + "set cubeprop_tasks [" + CubeProp + "]\n"
                    + cubeorb + "\n"
                    //+ "cubeprop_filepath ('./cubes')\n"
                    + "cubeprop(wfn)\n";
            movecube = cubemove;
        } else {
            cubes = "";
            movecube = "";
        }
        //log("cubes:"+ cubes);

        savexyz = molname + ".update_geometry()\n"
                + molname + ".geometry()\n"
                + molname + ".print_out()\n"
                + "\n"
                + "print('\\nOptimized geometry was saved in file .xyz')\n"
                + molname + ".save_xyz_file('" + molname + ".xyz', True)";

        moloc = "basis_ = wfn.basisset()\n"
                + "C_occ = wfn.Ca_subset(\"AO\", \"OCC\") # canonical C_occ coefficients\n"
                + "LocalP = core.Localizer.build(\"PIPEK_MEZEY\", basis_, C_occ) # Pipek-Mezey Localization\n"
                + "LocalB = core.Localizer.build(\"BOYS\", basis_, C_occ) # Boys Localization\n"
                + "LocalP.localize()\n"
                + "LocalB.localize()\n\n"
                + "P_M_C_occ = LocalP.L # local P_M C_occ coefficients\n"
                + "B_C_occ = LocalB.L # local Boys C_occ coefficients\n\n"
                + "print_out('1st canonical C_occ.')\n"
                + "wfn.Ca().print_out()\n\n"
                + "print_out('Pipek-Mezey Localized C_occ.')\n"
                + "P_M_C_occ.print_out()\n"
                + "print_out('Boys Localized C_occ.')\n"
                + "B_C_occ.print_out()\n";

        if (psi_xyz.contains("YES")) {
            xyzout = savexyz;
        } else {
            xyzout = "\n\nprint_out('Final geometry:')\n"
                    + "print_out(\"\\n\")\n"
                    + "print_out(" + molname + ".save_string_xyz())";
        }

        if (psi_prop.length() > 0) {
            psiprop = "oeprop(wfn, " + psi_prop + ")";
        } else {
            psiprop = "";
        }

        if (psi_local.length() > 0) {
            psiloc = moloc;
        } else {
            psiloc = "";
        }

        outputsall = psi_ther + "\n"
                + psi_moldenout + "\n"
                + psi_fchkout + "\n"
                + psi_gdma + "\n"
                + cubes + "\n"
                + psiprop + "\n"
                + psiloc + "\n"
                + xyzout + "\n\n"
                + movecube + "\n";

        outputsall = outputsall.replaceAll("(?m)^(null)?,", "");
        outputsall = outputsall.replaceAll("(?m)^[ \t]*\r?r?\n\n", "");

        return outputsall;
    }

}
