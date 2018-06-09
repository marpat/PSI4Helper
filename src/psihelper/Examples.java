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

public class Examples {

    

    public static String[] ExamplesCoord(String molecule) {
        String molret = "";
        String comments = "";
        String molnm = "";
        String hf;
        String menh2_com;
        String cdifethene_com;
        String tdifethene_com;
        String form;
        String form1;
        String form2;
        String form3;
        String nmf;
        String waterpsi;
        String eneine_sapt;
        String HCOHH2O;
        String waterdim;
        String azide_acet;

        hf = "H-F";
        String Hydrogenfluoride = "F\n"
                + "H   1   0.925\n";

        cdifethene_com = "CHF::CHF (cis) HF/6-31G*";
        String cDifluoroethene = "C\n"
                + "C   1   1.34\n"
                + "F   1   1.33   2  120.0\n"
                + "F   2   1.33   1  120.0  3    0.0\n"
                + "H   1   1.08   2  120.0  3  180.0\n"
                + "H   2   1.08   1  120.0  4  180.0";

        tdifethene_com = "CHF::CHF (trans) HF/6-31G*";
        String tDifluoroethene = "C\n"
                + "C   1   1.34\n"
                + "H   1   1.08   2  120.0\n"
                + "F   1   1.33   2  120.0  3  180.0\n"
                + "F   2   1.33   1  120.0  3    0.0\n"
                + "H   2   1.08   1  120.0  3  180.0";

        menh2_com = "Methylamine...RHF/3-21G//Pople-Gordon geometry";
        String Methylamine = "C\n"
                + "N   1   1.47\n"
                + "H   1   1.09   2   109.4712\n"
                + "H   1   1.09   2   109.4712   3   120. 0\n"
                + "H   1   1.09   2   109.4712   3   240. 0\n"
                + "H   2   1.01   1   109.4712   3    60. 0\n"
                + "H   2   1.01   1   109.4712   3   300. 0";

        form = "formamide (H2NCHO) in plane";
        String Formamide_XYZ = " N      0.000000       0.000000      -1.361000\n"
                + " C      0.000000       0.000000       0.000000\n"
                + " O      0.994000       0.000000       0.693400\n"
                + " H      0.878900       0.000000      -1.856600\n"
                + " H     -0.859100       0.000000      -1.886400\n"
                + " H     -1.023200       0.000000       0.422600";

        form1 = "formamide (H2NCHO) red. int. coord.";
        String Formamide_zmat = " N \n"
                + " C 1 1.361\n"
                + " O 2 1.212 1 124.90\n"
                + " H 1 1.009 2 119.42 3 0.00\n"
                + " H 1 1.007 2 121.45 3 180.00\n"
                + " H 2 1.107 1 112.44 4 180.00";

        form2 = "formamide (H2NCHO) non planar, complete z-matrix.";
        String Formamide_zmatfull = "N\n"
                + "C    1 R2\n"
                + "O    2 R3     1 A3\n"
                + "H    1 R4     2 A4     3 T4\n"
                + "H    1 R5     2 A5     3 T5\n"
                + "H    2 R6     1 A6     4 T6\n"
                + "\n"
                + "R2   =   1.26\n"
                + "R3   =   1.11\n"
                + "A3   = 134.\n"
                + "R4   =   1.09\n"
                + "A4   = 129.\n"
                + "T4   = 10.\n"
                + "R5   =   1.01\n"
                + "A5   = 121.\n"
                + "T5   =   158.\n"
                + "R6   =   1.10\n"
                + "A6   = 112.\n"
                + "T6   =   160.";

        form3 = "formamide (H2NCHO) planar, complete z-matrix.";
        String Form_zmat_plane = "N\n"
                + "C 1  B1\n"
                + "O 2  B2    1  A1\n"
                + "H 1  B3    2  A2    3  D1    0\n"
                + "H 1  B4    2  A3    3  D2    0\n"
                + "H 2  B5    1  A4    4  D3    0\n"
                + "\n"
                + "B1             1.361\n"
                + "B2             1.212\n"
                + "B3             1.009\n"
                + "B4             1.007\n"
                + "B5             1.107\n"
                + "A1           124.900\n"
                + "A2           119.650\n"
                + "A3           121.450\n"
                + "A4           112.022\n"
                + "D1             0.000\n"
                + "D2          -180.000\n"
                + "D3          -180.000\n";

        nmf = "trans-N-methylformamide (MeHNCHO), z-matrix";
        String NMF_zmat_plane = "N\n"
                + "C     1     B1\n"
                + "C     1     B2    2     A1\n"
                + "H     2     B3    1     A2    3     D1    0\n"
                + "H     2     B4    1     A3    3     D2    0\n"
                + "H     2     B5    1     A4    3     D3    0\n"
                + "H     1     B6    2     A5    3     D4    0\n"
                + "O     3     B7    1     A6    2     D5    0\n"
                + "H     3     B8    1     A7    2     D6    0\n"
                + "\n"
                + "B1      1.490\n"
                + "B2      1.361\n"
                + "B3      1.070\n"
                + "B4      1.070\n"
                + "B5      1.070\n"
                + "B6      1.070\n"
                + "B7      1.212\n"
                + "B8      1.107\n"
                + "A1    120.000\n"
                + "A2    109.4\n"
                + "A3    109.4\n"
                + "A4    109.4\n"
                + "A5    120.0\n"
                + "A6    124.9\n"
                + "A7    120.000\n"
                + "D1    120.000\n"
                + "D2   -120.000\n"
                + "D3      0.000\n"
                + "D4    180.0\n"
                + "D5      0.0\n"
                + "D6    180.0\n";

        waterdim = "Water dimer in Psi4 Examples (SAPT5)\n";
        String Water_G = 
                "O  -1.551007  -0.114520   0.000000\n"
                +"H  -1.934259   0.762503   0.000000\n"
                +"H  -0.599677   0.040712   0.000000\n"
                +"--\n"
                +"0 1\n"
                +"O   1.350625   0.111469   0.000000\n"
                +"H   1.680398  -0.373741  -0.758561\n"
                +"H   1.680398  -0.373741   0.758561\n"
                +"units angstrom";

        HCOHH2O = "Formamide-water dimer";
        String Water_FF = "H       -0.405941     -0.965812     -0.006924\n" 
                + "C       -0.816287      0.056585      0.005978\n" 
                + "O       -0.094892      1.043395      0.024952\n" 
                + "N       -2.165749      0.106502     -0.002251\n" 
                + "H       -2.631360      1.003351      0.011407\n" 
                + "H       -2.723607     -0.732896     -0.016328\n"
                +"--\n"
                +"0 1\n"
                +"H        3.165216      0.006852     -0.001388\n"
                +"O        2.305025     -0.424934      0.002933\n"
                +"H        1.641804      0.290328     -0.000982\n"
                +"units angstrom";

        eneine_sapt = "PSI4 SAPT for ethene*ethine";
        String EneIne_sapt = "C   0.000000  -0.667578  -2.124659\n"
                + "C   0.000000   0.667578  -2.124659\n"
                + "H   0.923621  -1.232253  -2.126185\n"
                + "H  -0.923621  -1.232253  -2.126185\n"
                + "H  -0.923621   1.232253  -2.126185\n"
                + "H   0.923621   1.232253  -2.126185\n"
                + "--\n"
                + "0 1\n"
                + "C   0.000000   0.000000   2.900503\n"
                + "C   0.000000   0.000000   1.693240\n"
                + "H   0.000000   0.000000   0.627352\n"
                + "H   0.000000   0.000000   3.963929\n"
                + "units angstrom";

        azide_acet = "TS of HN3*acetylene cycloaddition";
                String Azide_acet = "C  0.0000  0.0000  0.0000\n" 
                        + "H  0.0000  0.0000  1.0654\n" 
                        + "C  0.4726  0.0000 -1.1323\n" 
                        + "H  1.1594  0.0219 -1.9448\n"
                        + "--\n"
                        + "0 1\n"
                        + "N -2.0912 -0.0069 -0.2466\n" 
                        + "N -2.1297 -0.0165 -1.4073\n" 
                        + "N -1.3108  0.0935 -2.3599\n" 
                        + "H -1.6385 -0.3259 -3.2294\n"
                        + "units angstrom";
        
        waterpsi = "Water PSI4 examples";
        String Water_PSI = "O\n"
                + "H     1     1.0\n"
                + "H     1     1.0     2     104.5";
        
        switch (molecule) {
            case "H-F":
                molret = Hydrogenfluoride;
                comments = hf;
                molnm = "HF";
                break;
            case "cis_Difluoroethene":
                molret = cDifluoroethene;
                comments = cdifethene_com;
                molnm = "c-C2H2F2";
                break;
            case "trans_Difluoroethene":
                molret = tDifluoroethene;
                comments = tdifethene_com;
                molnm = "t-C2H2F2";
                break;
            case "Methylamine":
                molret = Methylamine;
                comments = menh2_com;
                molnm = "menh2";
                break;
            case "Formamide_XYZ":
                molret = Formamide_XYZ;
                comments = form;
                molnm = "formCart";
                break;
            case "Formamide_zmat":
                molret = Formamide_zmat;
                comments = form1;
                molnm = "formZmat";
                break;
            case "Formamide_zmatfull":
                molret = Formamide_zmatfull;
                comments = form2;
                molnm = "formZmatfull";
                break;
            case "Form_zmat_plane":
                molret = Form_zmat_plane;
                comments = form3;
                molnm = "formPlane";
                break;
            case "NMF_zmat_plane":
                molret = NMF_zmat_plane;
                comments = nmf;
                molnm = "NMF_plane";
                break;
            case "Water dimer in Psi4 Examples (SAPT5)":
                molret = Water_G;
                comments = waterdim;
                molnm = "dimer_water_psi";
                break;
            case "Formamide-water dimer":
                molret = Water_FF;
                comments = HCOHH2O;
                molnm = "dim_water_Formamide";
                break;
            case "Water PSI4 examples":
                molret = Water_PSI;
                comments = waterpsi;
                molnm = "water";
                break;
            case "PSI4 SAPT for ethene*ethine":
                molret = EneIne_sapt;
                comments = eneine_sapt;
                molnm = "C2H4_C2H2";
                break;
            case "TS of HN3*acetylene cycloaddition":
                molret = Azide_acet;
                comments = azide_acet;
                molnm = "C2H2_HN3";
                break;
        }

        String[] s = {molret, comments, molnm};
        //System.out.println(molret);
        return s;
    }
}
