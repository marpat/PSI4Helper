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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Date, author
 * Outputs.java (UTF-8)
 *
 * May 20, 2018 "
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */

// main class
public class Outputs extends FXMLDocumentController {

    // Check if file exists and copy
    private static void copyFile(File source,
            File dest) throws IOException {
        if (Files.notExists(dest.toPath())) {
            Files.copy(source.toPath(), dest.toPath());
        }
    }

//Replace words in Orbital string using a dictionary
    public String stringDict(String orb_string) {
        Map<String, String> map = new HashMap<>();
        map.put("-homo", "homo1m");
        map.put("-lumo", "lumo1m");
        
        orb_string = Arrays.stream(orb_string.toLowerCase().split(","))
        .map(s -> map.getOrDefault(s, s))
        .collect(Collectors.joining(","));

        return orb_string;
    }
    
 // Outputs method
    // <editor-fold defaultstate="collapsed" desc="main method from PSI.java">
    public String outputs(
            String file_name,
            String inp_dir,
            String psi_moldenout,
            String psi_47out,
            String psi_fchkout,
            String psi_gdma,
            String psi_xyz,
            String molname,
            String suff,
            String num_cube,
            String CubeProp,
            String psi_prop,
            String psi_local,
            String psi_ther,
            String writemol2,
            String jmol_path,
            String psi_jmolloc
    ) throws IOException {

        String outputsall = "";
        String xyzout = "";
        String savexyz;
        String cubes = "";
        String cubeorb;
        String psiprop = "";
        String psiloc = "";
        String dip47 = "";
        String moloc;
        String dipole47;
        String dipget;
        String eneget;
        String movecube;
        String cubemove;
        String countorb;
        String babel;
        String runbabel;
        String cahxyz;
        String jmol;
        String jpath;
// </editor-fold>
        
// <editor-fold defaultstate="collapsed" desc="vmd_cube scripts">
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
// </editor-fold>
        
        // Change slashes in PATH (to forward slashes)
        inp_dir = inp_dir.replace("\\", "/"); // for all platforms
        jmol_path = jmol_path.replace("\\", "/");
        // unify script directory
        String inp_diru = inp_dir.replace("\\", "/"); // for Jmol script

        //log("incoming num_cube");
        //log(num_cube);
        String orbitals = stringDict(num_cube);
        //log("\nJava method output");
        //log(orbitals);

        cubemove = "import os, re\n"
                + "from pathlib import Path\n"
                + "import shutil\n\n"
                + "now = str(Path().absolute())\n"
                + "if not os.path.exists(now+'/cubes'):\n"
                + "    os.makedirs(now+'/cubes')\n"
                + "moveto = now + '/cubes/'\n"
//                + "# remove old .cube files first\n"
//                + "try:\n"
//                + "    cubelist = [ c for c in os.listdir(moveto) if c.endswith(\".cube\") ]\n"
//                + "    for k in cubelist:\n"
//                + "        os.remove(os.path.join(moveto, k))\n"
//                + "    print(\"Removed old .cube files for ./cubes directory.\")\n"
//                + "except Exception as c:\n"
//                + "    print(\"Error in removing old .cube files: \" + str(c))\n"
//                + "    pass\n"
                + "files = []\n"
                + "for filename in os.listdir():\n"
                + "    if filename.endswith('.cube'):\n"
                + "        files.append(filename)\n"
                + "for f in files:\n"
                + "    try:\n"
                + "        fil = re.sub(\"'|\\\"\", \"\",f)\n"
                + "        src = now+'/'+f\n"
                + "        dst = moveto+'"+file_name+"_'+fil\n"
                + "        shutil.move(src,dst)\n"
                + "    except Exception as e:\n"
                + "        print(\"Move file error: \" + str(e))\n"
                + "        pass\n"
                + "\nprint('Moved {} .cube files to ./cubes directory.'.format(len(files)))\n"
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
                + "LUMO = np.asarray(wfn.epsilon_a_subset(\"AO\", \"ALL\"))[wfn.nalpha() + 1]\n"
                + "# Create variables for beta HOMO and LUMO orbitals\n"
                + "homo = int(ndocc)\n"
                + "lumo = (int(ndocc) +1)\n"
                + "homo1m = np.negative(homo)\n"
                + "lumo1m = np.negative(lumo)\n"
                + "orbitals = [" + orbitals + "]\n"
                + "\n"
                + "print('The HOMO - LUMO gap is: %16.8f a.u.' % (LUMO - HOMO))\n"
                + "\n"
                + "print('Number of doubly occupied orbitals:   %d' % ndocc)\n"
                + "print('Number of singly occupied orbitals:   %d' % nsocc)\n"
                + "print('Number of basis functions:            %d' % nbf)\n";

        if (num_cube.length() < 1) {
            cubeorb = "";
            countorb = "";
        } else {
            //    cubeorb = "set cubeprop_orbitals [" + num_cube + "]";
            cubeorb = "set cubeprop_orbitals $orbitals";
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
        
        if (psi_jmolloc.contains("YES")) {
            jpath = jmol_path;
        } else {
            jpath = "./cubes/";
        }

        //log("cubes:"+ cubes);
        savexyz = molname + ".update_geometry()\n"
                + molname + ".geometry()\n"
                + molname + ".print_out()\n"
                + "\n"
                + "print('\\nCurrent system geometry was saved in file .xyz')\n"
                + molname + ".save_xyz_file('" + file_name + suff + ".xyz', True)";

        // MO localization
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

        // Get dipole integrals and append them to file .47
        dipole47 = "# Get x,y,z-matrices of dipole integrals and append them to file .47\n"
                + "mints = psi4.core.MintsHelper(wfn.basisset())\n"
                + "nbf = mints.nbf()\n"
                + "f=open('dip.dat','ab') # temporary file; will be deleted\n"
                + "for i in range(3):\n"
                + "    D = np.asmatrix(mints.ao_dipole()[i])\n"
                + "    D[np.abs(D) < 1e-7] = 0\n"
                + "    Di = D * -0.52917725 # Bohr radius in Ao\n"
                + "    np.savetxt(f,Di, fmt='%6.7E')\n"
                + "f.close()\n\n"
                + "# Parse matrices and format them by five columns\n"
                + "data = np.loadtxt('dip.dat')\n"
                + "dipX = np.array(data[0:nbf, :]).flatten()\n"
                + "dipY = np.array(data[nbf:2*nbf, :]).flatten()\n"
                + "dipZ = np.array(data[2*nbf:2*nbf+nbf+1, :]).flatten()\n"
                + "listdip = [dipX, dipY, dipZ]\n"
                + "with open('dipR.dat',\"w\") as f:\n"
                + "    FOR = []\n" 
                + "    f.write(\" $DIPOLE\\n\")\n"
                + "    for j in range(3):\n"
                + "        for i in range(0, len(listdip[j].reshape(-1)), 5):\n"
                + "            # print(listdip[j][i:i + 5])  # DEBUG point\n"
                + "            FOR.append(listdip[j][i:i + 5])\n"
                + "    FORfmt = [[\"{: 6.6E}\".format(v) for v in r] for r in FOR]\n"
                + "    f.write(\"\\n\".join(\"  \".join(map(str, x)) for x in (FORfmt)))\n"
                + "    f.write(\"\\n $END\\n\")\n"
                + "f.close()\n\n"
                + "# Append the text file to the main blocks of .47\n"
                + "with open('" + file_name + suff + ".47', 'a') as outfile:\n"
                + "    with open('dipR.dat') as infile:\n"
                + "        outfile.write(infile.read())\n"
                + "outfile.close()\n"
                + "os.remove('dipR.dat')\n"
                + "print('Dipole matrices generated and appended to file .47.')\n"
                + "# Dipole processing ends-------\n"
                + "\n\n";

        // Calculate and print total dipole
        dipget = "\n"
                + "dipX = variable(\"CURRENT DIPOLE X\")\n"
                + "dipY = variable(\"CURRENT DIPOLE Y\")\n"
                + "dipZ = variable(\"CURRENT DIPOLE Z\")\n"
                + "\n"
                + "dipT = np.sqrt(np.square(dipX) + np.square(dipY) + np.square(dipZ))\n"
                + "print(\"\\nTotal dipole: {0:.2f} D\\n\".format(dipT))";

        eneget = "\n"
                + "eneT = variable(\"CURRENT ENERGY\")\n"
                + "print(\"\\nCurrent Energy: {0:.7f} a.u.\\n\".format(eneT))";

        //OpenBabel (writemol2 ==YES)
        babel = "\n"
                + "\nimport subprocess, sys, os, glob\n"
                + "\n# If cubes are checked, geom.xyz file is created. Remove it.\n"
                + "try:\n"
                + "    os.remove(\"geom.xyz\")\n"
                + "except Exception as r:\n"
                + "    print(\"Type error when removing geom.xyz file: \" + str(r))\n"
                + "    pass\n"
                + "\nkf = \"\"\"2\\n\n"
                + "\nF     0.00000    0.00000   0.00000\n"
                + "K   {:.5f}   {:.5f}   {:.5f}\"\"\".format(dipX, dipY, dipZ)\n"
                + "\ntry:\n"
                + "    with open(\"zzkf.xyz\", \"w+\") as f:\n"
                + "        f.write(kf)\n"
                + "except Exception as e:\n"
                + "    print(\"Type error: \" + str(e))\n"
                + "    pass\n"
                + "\nif len(glob.glob(\"*.xyz\")) > 2:\n"
                + "    print(\"There are > 2 .xyz files to merge by Open Babel.\")\n"
                + "    sys.exit(\"Exiting. Remove other .xyz files.\")\n"
                + "\ntry:\n"
                + "    cmd = 'obabel -ixyz *.xyz -omol2 -O " + file_name + suff + ".mol2 -j -c\'\n"
                + "    p = subprocess.Popen(cmd, shell=True, stderr=subprocess.PIPE)\n"
                + "    print(\"Sybyl .mol2 file with dipole vector was created.\")\n"
                + "except Exception as e:\n"
                + "    print(\"Type error when creating mol2 file: \" + str(e))\n"
                + "    pass\n";


        // <editor-fold defaultstate="collapsed" desc="Jmol macro files">
        // 
        jmol = "\n# Generate macro file for Jmol visualization.\n"
                + "\nimport sys, os, glob\n"
                + "\nnow = str(Path().absolute())\n"
                + "path_data = str(now+'/cubes')\n"
                + "a = glob.glob(path_data + '/*.*')\n"
                + "result_files=[]\n"
                + "result_files += [each.split('cubes/')[1] for each in a if each.endswith('.cube')]\n"
                + "\ntry:\n"
                + "    for j in enumerate(result_files):\n"
                + "        i = j[1].split(\".\")[0].strip(\"'\").strip('\"')\n"
                + "        print(i)\n"
                + "        if i.strip()[-1] == 'P':\n"
                + "            cut = '0.07'  # for potential\n"
                + "            color = ' sign color [255 0 0] [250 208 0]'\n"
                + "        elif i.strip()[-1] == 'A':\n"
                + "            cut = '0.07'  # for orbitals\n"
                + "            color = ' sign '\n"
                + "        elif i.strip()[-2] == 'D':\n"
                + "            cut = '0.002'  # for density\n"
                + "            color = ' sign color [255 0 0] [250 208 0]'\n"
                + "        else:\n"
                + "            cut = '0.85'  # for ELF'\n"
                + "            color = ' color [255 0 0]'\n"
                + "        OutputScript = 'Title=' + i + '\\nScript=reset; load \"" + inp_diru + "/cubes/' + i + '.cube\"; set labelfront; background [0 55 114]; isosurface cutoff '+ cut  +color+ ' \"" + inp_diru + "/cubes/' + i +'.cube\" translucent 0.3; show isosurface'\n"
                + "        with open(\""+jpath+"\" + i + \".macro\", \"w+\") as f:\n"
                + "            f.write(OutputScript)\n"
                + "    print(\"\\nCreated cube->macro files for Jmol visualization.\")\n"
                + "except Exception as e:\n"
                + "    print(\"Write error when creating Jmol .macro files: \" + str(e))\n";

        jmol = CubeProp.length() > 0 ? jmol : "";

        // if writemol2 == YES entered use 'mol'
        runbabel = writemol2.length() > 0 ? babel : "";

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
            dipget = "";
        }

        if (psi_local.length() > 0) {
            psiloc = moloc;
        } else {
            psiloc = "";
        }
        
        if (psi_47out.length() > 0) {
            dip47 = dipole47;
        } else {
            dip47 = "";
        }

        outputsall = psi_ther + "\n"
                + psi_moldenout + "\n"
                + psi_47out + "\n"
                + psi_fchkout + "\n"
                + psi_gdma + "\n"
                + cubes + "\n"
                + psiprop + "\n"
                + psiloc + "\n"
                + dip47 + "\n"
                + xyzout + "\n\n"
                + movecube + "\n"
                + jmol + "\n"
                + dipget + "\n"
                + eneget + "\n"
                + "\nprint_variables()\n" + "\n"
                + runbabel + "\n";

        outputsall = outputsall.replaceAll("(?m)^(null)?,", "");
        outputsall = outputsall.replaceAll("(?m)^[ \t]*\r?r?\n\n", "");

        return outputsall;
    }

}
