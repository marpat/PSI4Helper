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

 /*
 * FXMLDocumentController.java, part of the PsiHelper project
 *
 * PSI4 Helper is GUI interface that assists in creating content and format of PSI4 input files.
 * PSI4 Helper is written in JavaFlex included in JDK8 and running on JRE8.
 * Details of PSI4 compotational chemistry platform are at: https://http://www.psicode.org/
 */
package psihelper;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 * @version 1.0, 7/27/2018
 */
public class FXMLDocumentController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="@FXML declarations">
    // Defined in fxml file by Scene Builder
    @FXML
    private Button SetDirBn;
    @FXML
    private Label SetDirLb;
    @FXML
    private AnchorPane anchorid;
    @FXML
    private TextField Filename;
    @FXML
    private TextField Suffix;
    @FXML
    private TextField MolName;
    @FXML
    private TextField Memory;
    @FXML
    private TextField Cores;
    @FXML
    private ComboBox<String> PsiExamples;
    @FXML
    private ComboBox<String> PsiReference;
    @FXML
    private ComboBox<String> PsiBas;
    @FXML
    private ComboBox<String> PsiMethod;
    @FXML
    private ComboBox<String> PsiFunct;
    @FXML
    private ComboBox<String> ScfType;
    @FXML
    private ComboBox<String> AddOptions;
    @FXML
    private TextArea OptKingOptions;
    @FXML
    private ComboBox<String> AddKingOpt;
    @FXML
    private ComboBox<String> PsiSolvent;
    @FXML
    private ComboBox<String> PsiPrint;
    @FXML
    private ComboBox<String> PsiPoint;
    @FXML
    private Button ClearAll;
    @FXML
    private Button SaveLayout;
    @FXML
    private Button ExitBn;
    @FXML
    private Hyperlink link;
    @FXML
    private TextArea PsiGeom;
    @FXML
    private TextField PsiDescription;
    @FXML
    private TextField PubChem;
    @FXML
    private TextArea SetOptions;
    //private TextArea AreaOut;
    @FXML
    private TextField PsiCubeRange;
    @FXML
    private TextField PsiMulti;
    @FXML
    private TextField InpFile;
    @FXML
    private Button OpenDirBn;
    @FXML
    protected TextField PMwfn;
    @FXML
    protected TextField PM2aim;
    @FXML
    private TextField InpDir;
    @FXML
    private CheckBox Moldenout;
    @FXML
    protected CheckBox Psi47;
    @FXML
    protected CheckBox PsiWfx;
    @FXML
    private CheckBox Properties;
    @FXML
    private CheckBox Xyzout;
    @FXML
    private CheckBox Fchkout;
    @FXML
    private CheckBox Gdma;
    @FXML
    private CheckBox PsiLocal;
    @FXML
    private CheckBox PsiCubeDen;
    @FXML
    private CheckBox UseConstrains;
    @FXML
    private CheckBox PsiCubeOrb;
    @FXML
    private CheckBox PsiCubeEsp;
    @FXML
    private CheckBox PsiCubeElf;
    @FXML
    private CheckBox PsiCubeDua;
    @FXML
    private TextField Baspth;
    @FXML
    private CheckBox PsiPyapi;
    @FXML
    private TextField PsiCharge;
    @FXML
    private CheckBox PsiSp;
    @FXML
    private CheckBox PsiTs;
    @FXML
    private CheckBox PsiSapt;
    @FXML
    private CheckBox PsiOpt;
    @FXML
    private CheckBox PsiIrc;
    @FXML
    private CheckBox PsiFreq;
    @FXML
    private CheckBox PsiFreeze;
    @FXML
    private CheckBox PsiPuream;
    @FXML
    private CheckBox NatOrb;
    @FXML
    private CheckBox PsiPrbasis;
    @FXML
    private CheckBox PsiPrmos;
    @FXML
    private CheckBox PsiNocom;
    @FXML
    private CheckBox Psinoorient;
    @FXML
    private CheckBox PsiCp;
    @FXML
    private CheckBox PsiGrad;
    @FXML
    private TextArea RunOptions;
    @FXML
    private ComboBox<String> AddRun;
    @FXML
    private CheckBox PsiHes;
    @FXML
    private CheckBox PsiTher;
    @FXML
    private ChoiceBox<String> PsiProperties;
    @FXML
    private CheckBox PsiMol2;
    @FXML
    private CheckBox PsiJmol;
    @FXML
    private TextField Extension;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="My Global variables">
    String suff = null;
    String link3;
    String link2;
    String linkR;
    String linkT;
    String psi_charge;
    String psi_multi;
    String memory;
    String mybas ="";
    String cores;
    String psi_molcomment;
    String psi_geom = "";
    String molname;
    String psi_method;
    String psi_bas;
    String psi_solvent;
    String psi_freeze;
    String psi_ref;
    String psi_scftype;
    String psi_natorb;
    String psi_prmos;
    String psi_prbasis;
    Boolean psi_conf;
    String psi_puream;
    String psi_options;
    String psi_pubchem;
    String psi_point;
    String psi_local;
    String psi_call;
    String psi_sapt;
    String psi_irc;
    String opt_type = "";
    String psi_print;
    String set_alone = "";
    String psi_funct;
    String ingeo1;
    String ingeo2;
    String molecule;
    String psi_moldenout;
    String psi_fchkout;
    String psi_gdma;
    String psi_xyz;
    String psi_prop;
    String psi_pyapi;
    String addoptions;
    String king_options;
    String addrunopt;
    String cuberange;
    String psi_cuberange;
    String num_cube;
    String file_name;
    String inp_dir;
    String mwfn_path = null;
    String write47;
    String psi_47out;
    String writewfx;
    String writemol2;
    String m2aim_path;
    String mybaspth;
    String psi_cp;
    String psi_ther;
    String set_univ = "";
    String opt_freq = "";
    String resprop = "";
    String user_home;
    String psi_jmolloc;
    String jmol_path;
    String extension;
    String useconstrains;
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Combo boxes">
    // Boxes with ObservableArrayList objects
    
   //A section of pull-down menus and items
    // To add an item, insert it here and also lower down in PsiBas section
    
    Separator separatorH = new Separator(Orientation.HORIZONTAL);

    ObservableList<String> examplesbox = FXCollections.observableArrayList(
            "H-F", "Methylamine", "Acrolein_XYZ", "Formamide_XYZ", "Formamide_zmat", "Formamide_zmatfull", "Form_zmat_plane", "NMF_zmat_plane", "cis_Difluoroethene", "trans_Difluoroethene", "Water dimer in Psi4 Examples (SAPT5)", "Formamide-water dimer", "Water PSI4 examples", "PSI4 SAPT for ethene*ethine", "TS of HN3*acetylene cycloaddition", "HCN-HNC IRC examples at psi4/samples/opt-irc-2"
    );
    ObservableList<String> addrunbox = FXCollections.observableArrayList(
            "CLEAR", "dertype 2", "dertype 1", "dertype energy", "BSSE_all"
    );
    ObservableList<String> addkingbox = FXCollections.observableArrayList(
            "CLEAR", "Dynamic_Level 1", "Dynamic_Level 2", "Dynamic_Level 5","Opt_Coord Natural", "Opt_Coord Deloc", "Opt_Coord Both", "intrafrag_step_limit","step_type nr", "step_type rfo", "fixed_coord_force_constant", "frozen_cartesian list"
    );
    ObservableList<String> refbox = FXCollections.observableArrayList(
            "RHF", "ROHF", "UHF", "CUHF", "RKS", "UKS", "TWOCON", "MCSCF", "GENERAL"
    );
    ObservableList<String> basisbox = FXCollections.observableArrayList(
            "STO-3G", "3-21G", "6-31G(d)", "6-31+G(d)", "6-311++G(d,p)", "cc-pVDZ", "cc-pVTZ", "cc-pVQZ", "def2-TZVP", "jun-cc-pVDZ", "aug-cc-pVDZ", "aug-cc-pVTZ", "cc-pCVTZ", "_ANOTHER", "mybas.gbs"
    );
    ObservableList<String> methodbox = FXCollections.observableArrayList(
            "HF", "DFT", "MP2", "CC2", "CCSD", "CCSD(T)", "MP4", "FNO-MP4", "OMP2", "SAPT0", "SAPT(dft)", "SAPT2", "SAPT2+(3)", "SAPT2+(3)dMP2", "F-SAPT", "SAPT-CT"
    );
    ObservableList<String> functbox = FXCollections.observableArrayList(
            "B3LYP", "HF", "B3LYP-D", "B3LYP-D3", "B97-D", "WB97X-D", "M06", "M06-2X", "PBE0", "PBE-DH"
    );
    ObservableList<String> scftypebox = FXCollections.observableArrayList(
            "DF", "DIRECT", "PK", "OUT_OF_CORE", "FAST_DF", "CD", "INDEPENDENT"
    );
    ObservableList<String> addoptbox = FXCollections.observableArrayList(
            "CLEAR", "another basis set", "my basis set", "geom_maxiter 25", "geom_maxiter 50", "geom_maxiter 100", "mp2_type conv", "mp_type df", "mcscf_type conv", "e_convergence 8", "d_convergence 10", "r_convergence 10", "g_convergence tight", "g_convergence verytight", "full_hess_every 1", "full_hess_every 5", "irc_direction back", "df_scf_guess false", "SAPT-A", "SAPT(dft)", "SAPTx-CT", "fisapt_do_plot true"
    );
    ObservableList<String> solventbox = FXCollections.observableArrayList(
            "None", "water", "dmso", "acetonitrile", "thf", "dcm", "benzene"
    );
    ObservableList<String> printbox = FXCollections.observableArrayList(
            "Default", "1", "2", "3", "4", "5"
    );
    ObservableList<String> symbox = FXCollections.observableArrayList(
            "NA", "c1", "ci", "c2", "cs", "d2", "c2v", "c2h", "d2h"
    );
    ObservableList<String> propbox = FXCollections.observableArrayList(
            "NONE", "POLARIZABILITY", "ROTATION", "OSCILATOR_STRENGTH", "ROA"
    );



    // </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="myMethod getMatchingString">
    List<String> getMatchingStrings(List<String> list,
            String regex) {
        ArrayList<String> matches = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        for (String s : list) {
            if (p.matcher(s).matches()) {
                matches.add(s);
            }
        }
        return matches;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Load and Save values from XML">
    public void LoadParams() {
        Properties props = new Properties();
        InputStream is = null;

        // First try loading from the current directory
        File f = new File("psi_def.xml");
        if (f.isFile() && f.canRead()) {
            try {
                // Open the stream.
                is = new FileInputStream(f);
                // To read chars from it, use new InputStreamReader
                // and specify the encoding.
                try {
                    props.loadFromXML(is);
                    // Get properties and set them to fields
                    memory = props.getProperty("memory");
                    file_name = props.getProperty("file_name");
                    inp_dir = props.getProperty("inp_dir");
                    cores = props.getProperty("cores");
                    psi_charge = props.getProperty("psi_charge");
                    psi_multi = props.getProperty("psi_multi");
                    mwfn_path = props.getProperty("Multiwfn");
                    m2aim_path = props.getProperty("Molden2Aim");
                    mybaspth = props.getProperty("mybaspth");
                    extension = props.getProperty("extension");

                    InpDir.setText(inp_dir);
                    Filename.setText(file_name);
                    Memory.setText(memory);
                    Cores.setText(cores);
                    PsiCharge.setText(psi_charge);
                    PsiMulti.setText(psi_multi);
                    PMwfn.setText(mwfn_path);
                    PM2aim.setText(mwfn_path);
                    Baspth.setText(mybaspth);
                    Extension.setText(extension);
                } finally {
                    is.close();
                }
            } catch (IOException ex) {
                // Appropriate error handling here.
            }
        } else {
            InpDir.setText(System.getProperty("user.home"));
            Filename.setText("test");
            Memory.setText("2");
            Cores.setText("1");
            PsiCharge.setText("0");
            PsiMulti.setText("1");
            PMwfn.setText("Set path to Mwfn");
            PM2aim.setText("Set path to M2aim");
            Baspth.setText("Set directory of your custom basis sets");
            Extension.setText("inp");
        }
    }

    public void saveParamChangesAsXML() {
        try {
            Properties props = new Properties();
            props.setProperty("memory", Memory.getText());
            props.setProperty("file_name", "" + Filename.getText());
            props.setProperty("inp_dir", "" + InpDir.getText());
            props.setProperty("cores", "" + Cores.getText());
            props.setProperty("psi_charge", "" + PsiCharge.getText());
            props.setProperty("psi_multi", "" + PsiMulti.getText());
            props.setProperty("Multiwfn", "" + PMwfn.getText());
            props.setProperty("Molden2Aim", "" + PM2aim.getText());
            props.setProperty("mybaspth", "" + Baspth.getText());
            props.setProperty("extension", "" + Extension.getText());

//            String path = PsiHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//            String decodedPath = URLDecoder.decode(path, "UTF-8");
//            String decodedPath1 = (new File(decodedPath)).getParentFile().getPath();
//            log(decodedPath1);
//            File f = new File(decodedPath1 + File.separator + "psi_def.xml");
            File f = new File("psi_def.xml");
            OutputStream out = new FileOutputStream(f);
            props.storeToXML(out, "Values from the last PSI4 input file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>

    @Override
    public void initialize(URL url,
            ResourceBundle rb) {
        PsiExamples.setItems(examplesbox);
        PsiReference.setItems(refbox);
        PsiReference.setValue("RHF");
        PsiBas.setItems(basisbox);
        PsiBas.setValue("STO-3G");
        PsiMethod.setItems(methodbox);
        PsiMethod.setValue("HF");
        PsiFunct.setItems(functbox);
        PsiFunct.setValue("B3LYP");
        ScfType.setItems(scftypebox);
        ScfType.setValue("DF");
        AddOptions.setItems(addoptbox);
        AddKingOpt.setItems(addkingbox);
        AddRun.setItems(addrunbox);
        PsiSolvent.setItems(solventbox);
        PsiSolvent.setValue("None");
        PsiPrint.setItems(printbox);
        PsiPrint.setValue("Default");
        PsiPoint.setItems(symbox);
        PsiPoint.setValue("NA");
        PsiProperties.setItems(propbox);
        PsiProperties.setValue("NONE");
        LoadParams();

        // Three checkboxes alter
        PsiSp.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PsiOpt.setSelected(false);
                PsiGrad.setSelected(false);
            }
        });

        PsiOpt.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PsiSp.setSelected(false);
                PsiGrad.setSelected(false);
            }
        });

        PsiGrad.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PsiSp.setSelected(false);
                PsiOpt.setSelected(false);
            }
        });
        PsiGrad.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (!isNowSelected) {
                PsiSp.setSelected(true);
                PsiOpt.setSelected(false);
            }
        });
        PsiIrc.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PsiSp.setSelected(false);
                PsiOpt.setSelected(true);
            }
        });
        PsiIrc.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (!isNowSelected) {
                PsiSp.setSelected(true);
                PsiOpt.setSelected(false);
            }
        });
        PsiTs.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PsiSp.setSelected(false);
                PsiOpt.setSelected(true);
            }
        });
        PsiTs.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (!isNowSelected) {
                PsiSp.setSelected(true);
                PsiOpt.setSelected(false);
            }
        });
    }

    @FXML // Load Directory
    private void setOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try {
        directoryChooser.setInitialDirectory(new File(inp_dir));
        }
        catch (Exception e) {
            log("Enter 'run' directory path manually and re-try.");
        }
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File selectedDirectory
                = directoryChooser.showDialog(stage);
        if (selectedDirectory == null) {
            InpDir.setText("No Directory selected");
        } else {
            InpDir.setText(selectedDirectory.getAbsolutePath());
            String inp_dir = selectedDirectory.getAbsolutePath();  //Change path into string
            inp_dir = inp_dir.replace("\\", "/"); //reverse backslashes for Jmol
        }
    }

    @FXML // Load example geometries
    private void PsiExamplesAction(ActionEvent event) {
        String molecule = PsiExamples.getValue();
        String[] molgeom = Examples.ExamplesCoord(molecule);

        PsiGeom.setText(molgeom[0]);

        if (PsiExamples.getValue().length() != 0) {
            //Filename.setText(molecule);
            PsiDescription.setText(molgeom[1]);
            MolName.setText(molgeom[2]);
        }

    }

    @FXML //Add more Options
    private void AddOptionsAction(ActionEvent event) {
        // Adding more options into Set directive
        String addoptions = AddOptions.getValue();
        SetOptions.setStyle("-fx-text-fill: #3300FF");
        switch (addoptions) {
            case "CLEAR":
                addoptions = SetOptions.getText() + "";
                SetOptions.setText("");
                break;
            case "another basis set":
                addoptions = SetOptions.getText() + "# Select _ANOTHER from the `Basis set` menu and type in one of the PSI4 basis sets.  Remove `e.g.,`\n'basis' : 'e.g., 6-311+G(2df,2p)',\n";
                SetOptions.setText(addoptions);
                break;
            case "my basis set":
                addoptions = SetOptions.getText() + "# Select `mybas.gbs` from the `Basis set` menu and enter filename\n# of your .gbs file without extension. Remove `e.g.,`.\n'basis' : 'e.g., mybs',\n";
                SetOptions.setText(addoptions);
                break;
            case "geom_maxiter 25":
                addoptions = SetOptions.getText() + "'geom_maxiter' : '25',\n";
                SetOptions.setText(addoptions);
                break;
            case "geom_maxiter 50":
                addoptions = SetOptions.getText() + "'geom_maxiter' : '50',\n";
                SetOptions.setText(addoptions);
                break;
            case "geom_maxiter 100":
                addoptions = SetOptions.getText() + "'geom_maxiter' : '100',\n";
                SetOptions.setText(addoptions);
                break;
            case "mp2_type conv":
                addoptions = SetOptions.getText() + "'mp2_type' : 'conv',\n";
                SetOptions.setText(addoptions);
                break;
            case "mp_type df":
                addoptions = SetOptions.getText() + "'mp_type' : 'df',\n";
                SetOptions.setText(addoptions);
                break;
            case "mcscf_type conv":
                addoptions = SetOptions.getText() + "'mcscf_type' : 'conv',\n";
                SetOptions.setText(addoptions);
                break;
            case "e_convergence 8":
                addoptions = SetOptions.getText() + "'e_convergence': '8',\n";
                SetOptions.setText(addoptions);
                break;
            case "d_convergence 10":
                addoptions = SetOptions.getText() + "'d_convergence': '10',\n";
                SetOptions.setText(addoptions);
                break;
            case "r_convergence 10":
                addoptions = SetOptions.getText() + "'r_convergence': '10',\n";
                SetOptions.setText(addoptions);
                break;
            // g_convergence gau_tight, full_hess_every 10
            case "g_convergence tight":
                addoptions = SetOptions.getText() + "'g_convergence': 'gau_tight',\n";
                SetOptions.setText(addoptions);
                break;
            case "g_convergence verytight":
                addoptions = SetOptions.getText() + "'g_convergence': 'gau_verytight',\n";
                SetOptions.setText(addoptions);
                break;
            case "full_hess_every 1":
                addoptions = SetOptions.getText() + "'full_hess_every': '1',\n";
                SetOptions.setText(addoptions);
                break;
            case "full_hess_every 5":
                addoptions = SetOptions.getText() + "'full_hess_every': '5',\n";
                SetOptions.setText(addoptions);
                break;
            case "irc_direction back":
                addoptions = SetOptions.getText() + "'irc_direction': 'backward',\n";
                SetOptions.setText(addoptions);
                break;
            case "df_scf_guess false":
                addoptions = SetOptions.getText() + "'df_scf_guess' : 'false',\n";
                SetOptions.setText(addoptions);
                break;
            case "SAPT-A":
                addoptions = "'guess' : 'sad',\n"
                        + "'freeze_core': 'true' # for larger molecules,\n";
                SetOptions.setText(addoptions);
                break;
            case "SAPT(dft)":
                addoptions
                        = "'SAPT_DFT_FUNCTIONAL' : '" + PsiFunct.getValue() + "' #  functional to use for SAPT(DFT),\n"
                        + "'guess' : 'sad',\n"
                        + "'freeze_core': 'true' # for larger molecules,\n";
                SetOptions.setText(addoptions);
                break;
            case "SAPTx-CT":
                addoptions = "# Use sapt_x-ct method,\n"
                        + "'df_basis_scf' : '" + PsiBas.getValue() + "'-jkfit',\n"
                        + "'df_basis_sapt' : '" + PsiBas.getValue() + "'-ri',\n"
                        + "'df_basis_elst' : '" + PsiBas.getValue() + "'-jkfit',\n"
                        + "'guess' : 'sad',\n"
                        + "'puream' : 'true,'\n"
                        + "'print' : '1',\n"
                        + "'basis_guess' : 'true',\n"
                        + "'freeze_core': 'true' # for larger molecules,\n";
                SetOptions.setText(addoptions);
                break;
            case "fisapt_do_plot true":
                addoptions = SetOptions.getText() + "'fisapt_do_plot' : 'true',\n";
                SetOptions.setText(addoptions);
                break;
        }
    }

    @FXML // Append Run Options
    private void AddRunOptions(ActionEvent event) {
        String addrunopt = AddRun.getValue();
        RunOptions.setStyle("-fx-text-fill: #8800CC");
        switch (addrunopt) {
            case "CLEAR":
                addrunopt = RunOptions.getText() + "";
                RunOptions.setText("");
                break;
            case "dertype 2":
                addrunopt = RunOptions.getText() + "'dertype=' : '2', ";
                RunOptions.setText(addrunopt);
                break;
            case "dertype 1":
                addrunopt = RunOptions.getText() + "'dertype=' : '1', ";
                RunOptions.setText(addrunopt);
                break;
            case "dertype energy":
                addrunopt = RunOptions.getText() + "'dertype=' : 'energy', ";
                RunOptions.setText(addrunopt);
                break;
            case "BSSE_all":
                addrunopt = RunOptions.getText() + "bsse_type=' : '['nocp', 'cp', 'vmfc']', ";
                RunOptions.setText(addrunopt);
                break;
        }

    }

    @FXML // Constrains add King options
    private void AddKingOptions(ActionEvent event) {
    //"intrafrag_step_limit","step_type nr", "step_type rfo", "fixed_coord_force_constant", 
        king_options = AddKingOpt.getValue();
        OptKingOptions.setStyle("-fx-text-fill: #8800CC");
        switch (king_options) {
            case "CLEAR":
                king_options = OptKingOptions.getText() + "";
                OptKingOptions.setText("");
                break;
            case "Dynamic_Level 1":
                king_options = OptKingOptions.getText() + "'DYNAMIC_LEVEL =' : '1',";
                OptKingOptions.setText(king_options);
                break;
        case "Dynamic_Level 2":
                king_options = OptKingOptions.getText() + "'DYNAMIC_LEVEL =' : '2',";
                OptKingOptions.setText(king_options);
                break;
        case "Dynamic_Level 5":
                king_options = OptKingOptions.getText() + "'DYNAMIC_LEVEL =' : '5',";
                OptKingOptions.setText(king_options);
                break;
        case "Opt_Coord Natural":
                king_options = OptKingOptions.getText() + "'OPT_COORDINATES =' : 'NATURAL',";
                OptKingOptions.setText(king_options);
                break;
        case "Opt_Coord Both":
                king_options = OptKingOptions.getText() + "'OPT_COORDINATES =' : 'BOTH',";
                OptKingOptions.setText(king_options);
                break;
        case "Opt_Coord Deloc":
                king_options = OptKingOptions.getText() + "'OPT_COORDINATES=' : 'DELOCALIZED',";
                OptKingOptions.setText(king_options);
                break;
        case "intrafrag_step_limit":
                king_options = OptKingOptions.getText() + "'intrafrag_step_limit =' : '0.1' # default,";
                OptKingOptions.setText(king_options);
                break;
        case "step_type nr":
                king_options = OptKingOptions.getText() + "'step_type =' : 'nr',";
                OptKingOptions.setText(king_options);
                break;
        case "step_type rfo":
                king_options = OptKingOptions.getText() + "'step_type =' : 'rfo',";
                OptKingOptions.setText(king_options);
                break;
        case "fixed_coord_force_constant":
                king_options = OptKingOptions.getText() + "'fixed_coord_force_constant =' : '0.5' # 0.5 to 200,";
                OptKingOptions.setText(king_options);
                break;
        case "frozen_cartesian list":
                king_options = OptKingOptions.getText() + "'frozen_cartesian =' : '(\" \")' # atom# x[y][z],";
                OptKingOptions.setText(king_options);
                break;
        }
    }
   
    @FXML// Save Layout
    private void SaveLayoutAction(ActionEvent event) {
        file_name = Filename.getText();
        inp_dir = InpDir.getText();
        extension = Extension.getText().length() > 0 ? Extension.getText() : "inp";
        if ("_".equals(Suffix.getText())) {
            suff = "";
        } else {
            suff = Suffix.getText().trim();
        }

    // PSI input settings
        String set_alone = "";
        String psi_call = "";
        String psi_pubchem = "";
        
    // Set OS specific User path
    user_home = System.getProperty("user.home");

//// <editor-fold defaultstate="collapsed" desc="PsiBas[psi_bas]">
        //String psi_bas = PsiBas.getValue();
        //PsiBas.setValue("STO-3G");
        switch (PsiBas.getValue()) {
            case "Basis Set":
                psi_bas = "'basis' : 'STO-3G'";
                mybas = "";
                break;
            case "STO-3G":
                psi_bas = "'basis' : 'STO-3G'";
                mybas = "";
                break;
            case "3-21G":
                psi_bas = "'basis' : '3-21G'";
                mybas = "";
                break;
            case "6-31G(d)":
                psi_bas = "'basis' : '6-31G(d)'";
                mybas = "";
                break;
            case "6-31+G(d)":
                psi_bas = "'basis' : '6-31+G(d)'";
                mybas = "";
                break;
            case "6-311++G(d,p)":
                psi_bas = "'basis' : '6-311++G(d,p)'";
                mybas = "";
                break;
            case "cc-pVDZ":
                psi_bas = "'basis' : 'cc-pVDZ'";
                mybas = "";
                break;
            case "cc-pVTZ":
                psi_bas = "'basis' : 'cc-pVTZ'";
                mybas = "";
                break;
            case "cc-pVQZ":
                psi_bas = "'basis' : 'cc-pVQZ'";
                mybas = "";
                break;
            case "def2-TZVP":
                psi_bas = "'basis' : 'def2-TZVP'";
                mybas = "";
                break;
            case "jun-cc-pVDZ":
                psi_bas = "'basis' : 'jun-cc-pVDZ'";
                mybas = "";
                break;
            case "aug-cc-pVDZ":
                psi_bas = "'basis' : 'aug-cc-pVDZ'";
                mybas = "";
                break;
            case "aug-cc-pVTZ":
                psi_bas = "'basis' : 'aug-cc-pVTZ'";
                mybas = "";
                break;
            case "cc-pCVTZ":
                psi_bas = "'basis' : 'cc-pCVTZ'";
                mybas = "";
                break;
            case "_ANOTHER":
                psi_bas = "'# Select `another basis set`' : 'from `*More Options` menu and save the .inp file again.'";
                break;
            case "mybas.gbs":
                psi_bas = "'# Select `my basis set`' : 'from `*More Options` menu and save the .inp file again.'";
                mybas = "import os\n\nos.environ['PSIPATH'] += os.pathsep + os.pathsep.join(['" + mybaspth + "'])\n";
                break;
        };
//// </editor-fold>        

// <editor-fold defaultstate="collapsed" desc="PsiMethod [psi_method]">
        //String psi_method = PsiMethod.getValue();
        //PsiMethod.setValue("HF");
        switch (PsiMethod.getValue()) {
            case "HF":
                psi_method = "scf";
                link2 = "";
                break;
            case "DFT":
                psi_method = "DFT";
                link2 = "";
                break;
            case "MP2":
                psi_method = "MP2";
                link2 = "";
                break;
            case "CC2":
                psi_method = "CC2";
                link2 = "";
                break;
            case "CCSD":
                psi_method = "CCSD";
                link2 = "";
                break;
            case "CCSD(T)":
                psi_method = "CCSD(T)";
                link2 = "";
                break;
            case "MP4":
                psi_method = "MP4";
                link2 = "";
                break;
            case "FNO-MP4":
                psi_method = "FNO-MP4";
                link2 = "";
                break;
            case "OMP2":
                psi_method = "OMP2";
                link2 = "";
                break;
            case "SAPT0":
                psi_method = "SAPT0";
                link2 = "";
                break;
            case "SAPT(dft)":
                psi_method = "SAPT(dft)";
                link2 = "";
                break;
            case "SAPT2":
                psi_method = "SAPT2";
                link2 = "";
                break;
            case "SAPT+(3)":
                psi_method = "SAPT+(3)";
                link2 = "";
                break;
            case "SAPT2+(3)dMP2":
                psi_method = "SAPT2+(3)dMP2";
                link2 = "";
                break;
            case "F-SAPT":
                psi_method = "fisapt0";
                link2 = "";
                break;
            case "SAPT-CT":
                psi_method = "sapt-ct";
                link2 = "";
                break;
        }
//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="PsiReference [psi_ref]">      
        //String psi_ref = PsiReference.getValue();
        //PsiReference.setValue("RHF");
        switch (PsiReference.getValue()) {
            case "RHF":
                psi_ref = "'reference' : 'rhf'";
                linkR = "";
                break;
            case "ROHF":
                psi_ref = "'reference' : 'rohf'";
                linkR = "";
                break;
            case "UHF":
                psi_ref = "'reference' : 'uhf'";
                linkR = "";
                break;
            case "CUHF":
                psi_ref = "CUHF";
                linkR = "";
                break;
            case "RKS":
                psi_ref = "RKS";
                linkR = "";
                break;
            case "UKS":
                psi_ref = "UKS";
                linkR = "";
                break;
            case "TWOCON":
                psi_ref = "TWOCON";
                linkR = "";
                break;
            case "MCSCF":
                psi_ref = "MCSCF";
                linkR = "";
                break;
            case "GENERAL":
                psi_ref = "GENERAL";
                linkR = "";
                break;
        }
// </editor-fold>
//
// <editor-fold defaultstate="collapsed" desc="ScfType [psi_scftype]">
        //String psi_scftype = ScfType.getValue();
        //ScfType.setValue("DF");
        switch (ScfType.getValue()) {
            case "DF":
                psi_scftype = "'scf_type' : 'DF'";
                linkT = "";
                break;
            case "DIRECT":
                psi_scftype = "'scf_type' : 'DIRECT'";
                linkT = "";
                break;
            case "PK":
                psi_scftype = "'scf_type' : 'PK'";
                linkT = "";
                break;
            case "OUT_OF_CORE":
                psi_scftype = "OUT_OF_CORE";
                linkT = "";
                break;
            case "FAST_DF":
                psi_scftype = "FAST_DF";
                linkT = "";
                break;
            case "INDEPENDENT":
                psi_scftype = "INDEPENDENT";
                linkT = "";
                break;
            case "CD":
                psi_scftype = "CD";
                linkT = "";
                break;
        }

// </editor-fold>
//
// <editor-fold defaultstate="collapsed" desc="PsiPrint [psi_print]">
        //String psi_print = PsiPrint.getValue();
        // PsiPrint.setValue("Default");
        switch (PsiPrint.getValue()) {
            case "Default":
                psi_print = "";
                break;
            case "1":
                psi_print = "'PRINT' : '1'";
                break;
            case "2":
                psi_print = "'PRINT' : '2'";
                break;
            case "3":
                psi_print = "'PRINT' : '3'";
                break;
            case "4":
                psi_print = "'PRINT' : '4'";
                break;
            case "5":
                psi_print = "'PRINT' : '5'";
                break;
        }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="DFT Functionals [psi_funct; no switch] ">
        psi_funct = PsiFunct.getValue();
        //PsiFunct.setValue("B3LYP");
//        switch (PsiFunct.getValue()) {
//            case "B3LYP":
//                psi_funct = "B3LYP";
//                link2 = "";
//                break;
//            case "B3LYP-D":
//                psi_funct = "B3LYP-D";
//                link2 = "";
//                break;
//            case "B3LYP-D3":
//                psi_funct = "B3LYP-D3";
//                link2 = "";
//                break;
//            case "B97-D":
//                psi_funct = "B97-D";
//                link2 = "";
//                break;
//            case "M06":
//                psi_funct = "M06";
//                link2 = "";
//                break;
//            case "M06-2X":
//                psi_funct = "M06-2X";
//                link2 = "";
//                break;
//            case "PBE0":
//                psi_funct = "PBE0";
//                link2 = "";
//                break;
//            case "PBE0-DH":
//                psi_funct = "PBE0-DH";
//                link2 = "";
//                break;
//        }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Symmetry [psi_point]">
// in case that selection == value, there is no need for switch-case        
        psi_point = PsiPoint.getValue();
        if (psi_point == "NA") {
            psi_point = "";
        }
        //PsiPoint.setValue("c1");
//        switch (PsiPoint.getValue()) {
//            case "c1":
//                psi_point = "c1";
//                break;
//            case "ci":
//                psi_point = "ci";
//                break;
//            case "c2":
//                psi_point = "c2";
//                break;
//            case "cs":
//                psi_point = "cs";
//                break;
//            case "d2":
//                psi_point = "d2";
//                break;
//            case "c2v":
//                psi_point = "c2v";
//                break;
//            case "c2h":
//                psi_point = "c2h";
//                break;
//            case "d2h":
//                psi_point = "d2h";
//                break;
//            case "NA":
//                psi_point = "";
//                break;
//        }
        // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Response Properties [resprop]">
        switch (PsiProperties.getValue()) {
            case "NONE":
                resprop = "";
                break;
            case "POLARIZABILITY":
                resprop = "'polarizability'";
                break;
            case "ROTATION":
                resprop = "rotation'";
                break;
            case "OSCILATOR_STRENGTH":
                resprop = "'oscilator_strength'";
                break;
            case "ROA":
                resprop = "'roa'";
                break;
        }

// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="PsiSolvent [psi_solvent]">
        //psi_solvent = PsiSolvent.getValue();
        // PsiSolvent.setValue("None");
        switch (PsiSolvent.getValue()) {
            case "None":
                psi_solvent = "";
                break;
            case "water":
                psi_solvent = "water";
                break;
            case "dmso":
                psi_solvent = "dmso";
                break;
            case "acetonitrile":
                psi_solvent = "acetonitrile";
                break;
            case "thf":
                psi_solvent = "thf";
                break;
            case "dcm":
                psi_solvent = "dcm";
                break;
            case "benzene":
                psi_solvent = "benzene";
                break;
        }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="AddOptions [addoptions]">
        addoptions = SetOptions.getText();
        king_options = OptKingOptions.getText();
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="AddRunOptions [addrunopt]">
        addrunopt = RunOptions.getText();
// </editor-fold>

// charge multiplicity memory cores comment
// <editor-fold defaultstate="collapsed" desc="charge multiplicity memory cores comment">
        if (PsiCharge.getText().length() == 0) {
            psi_charge = "0";
        } else {
            psi_charge = PsiCharge.getText();
        }

        if (PsiMulti.getText().length() == 0) {
            psi_multi = "1";
        } else {
            psi_multi = PsiMulti.getText();
        }

        if (Memory.getText().length() == 0) {
            memory = "2";
        } else {
            memory = Memory.getText();
        }

        if (Cores.getText().length() == 0) {
            cores = "2";
        } else {
            cores = Cores.getText();
        }

        if (PsiDescription.getText().length() == 0) {
            psi_molcomment = "";
        } else {
            psi_molcomment = PsiDescription.getText();
        }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="geometry PubChem">    
        if (PubChem.getText().length() == 0) {
            psi_geom = PsiGeom.getText();
           } 
        else if (!psi_geom.contains(".xyz") || !psi_geom.contains(".mol2")) {
            psi_geom = "pubchem: " + PubChem.getText();
            molname = PubChem.getText();
            MolName.setText(molname);
           }
        else {
        System.err.println("Something is wrong.");
        psi_geom = "Enter structure coordinates.";             } 
        
        molname = MolName.getText();
        // Continues on line 1318
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="check boxes">
        if (PsiPyapi.isSelected()) {
            psi_pyapi = "YES";
        } else {
            psi_pyapi = "NO";
        }
        if (PsiTs.isSelected()) {
            opt_type = "'opt_type' : 'ts'";
            psi_call = "optimize";
        } else {
            opt_type = "";
            psi_call = "";
        }
        if (PsiSp.isSelected()) {
            psi_call = "energy";
        }
        if (PsiGrad.isSelected()) {
            psi_call = "gradient";
        }
        if (PsiOpt.isSelected() && PsiFreq.isSelected()) {
            PsiFreq.setSelected(true);
            opt_freq = "optfreq";
        } else {
            opt_freq = "";
        }
        if (PsiOpt.isSelected()) {
            psi_call = "optimize";
        }
        if (PsiHes.isSelected()) {
            psi_call = "hessian";
        }
        if (PsiFreq.isSelected()) {
            psi_call = "frequencies";
            set_univ = "'normal_modes_write' : 'true'";
        } else {
            set_univ = "";
        }
        if (PsiTher.isSelected()) {
            psi_call = "frequencies";
            psi_ther = "set t 273.15\n"
                    + "set p 100000\n\n"
                    + "thermo(wfn, wfn.frequencies())\n";
        } else {
            psi_ther = "";
        }

        if (PsiIrc.isSelected()) {
            psi_irc = "YES";
            psi_call = "optimize";
        } else {
            psi_irc = null;
        }
        if (PsiCp.isSelected()) {
            psi_cp = "'bsse_type' : 'cp'";
        } else {
            psi_cp = null;
        }
        if (PsiSapt.isSelected()) {
            psi_sapt = "YES";
        } else {
            psi_sapt = null;
        }
        if (PsiNocom.isSelected()) {
            ingeo1 = "\nno_com\n";
        } else {
            ingeo1 = "";
        }
        if (Psinoorient.isSelected()) {
            ingeo2 = "\nno_reorient\n";
        } else {
            ingeo2 = "";
        }
        if (PsiFreeze.isSelected()) {
            psi_freeze = "'freeze_core': 'true'";
        } else {
            psi_freeze = "";
        }
        if (PsiPuream.isSelected()) {
            psi_puream = "'PUREAM': 'true'";
        } else {
            psi_puream = "";
        }
        if (NatOrb.isSelected()) {
            psi_natorb = "'WRITE_NOS': 'true'";
        } else {
            psi_natorb = "";
        }
        if (PsiPrbasis.isSelected()) {
            psi_prbasis = "'print_basis': 'true'";
        } else {
            psi_prbasis = "";
        }
        if (PsiPrmos.isSelected()) {
            psi_prmos = "'print_mos': 'true'";
        } else {
            psi_prmos = "";
        }
        if (Moldenout.isSelected() || Psi47.isSelected() || PsiWfx.isSelected()) {
            psi_moldenout = "molden(wfn, '" + file_name + suff + ".molden')";
        } else {
            psi_moldenout = "";
        }
        if (Psi47.isSelected()) {
            write47 = "YES";
            psi_47out = "# Let's make sure that new output won't be appended to an older .47 file.\n"
                    + "try:\n"
                    + "    os.remove('" + file_name + suff + ".47')\n"
                    + "except OSError as e:\n"
                    + "    print (\"Warning: %s - %s.\" % (e.filename, e.strerror))\n"
                    + "    print('Continuing ...')\n\n"
                    + "m = NBOWriter(wfn)\n"
                    + "m.write('" + file_name + suff + ".47')";
        } else {
            write47 = null;
            psi_47out = "";
        }
        if (PsiWfx.isSelected()) {
            writewfx = "YES";
            psi_puream = "'PUREAM': 'true'";
        } else {
            writewfx = null;
        }
        if (Fchkout.isSelected()) {
            psi_fchkout = "fchk(wfn, '" + file_name + suff + ".fchk')";
        } else {
            psi_fchkout = "";
        }
        if (Gdma.isSelected()) {
            psi_gdma = "gdma(wfn)";
        } else {
            psi_gdma = "";
        }
        if (Xyzout.isSelected()) {
            psi_xyz = "YES";
        } else {
            psi_xyz = "";
        }
        if (PsiLocal.isSelected()) {
            psi_local = "YES";
        } else {
            psi_local = "";
        }
        if (Properties.isSelected()) {
            psi_prop = "'DIPOLE', 'QUADRUPOLE', 'MULLIKEN_CHARGES', 'LOWDIN_CHARGES', 'WIBERG_LOWDIN_INDICES', 'NO_OCCUPATIONS'";
        } else {
            psi_prop = "";
        }
        if (PsiMol2.isSelected()) {
            writemol2 = "YES";
            psi_xyz = "YES";
            psi_prop = "'DIPOLE'";
        } else {
            writemol2 = "";
        }
        if (PsiJmol.isSelected()) {
            psi_jmolloc = "YES";
            jmol_path = user_home + System.getProperty("file.separator")+ ".jmol"+System.getProperty("file.separator")+"macros"+System.getProperty("file.separator");
        } else {
            psi_jmolloc = "NO";
            jmol_path = "./cubes";
        }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Set default variables">
        if (psi_geom.contains(".xyz") || psi_geom.contains(".mol2")) {
            String[] tokens = psi_geom.split("\\.(?=[^\\.]+$)");
            MolName.setText(tokens[0]);
            molname = MolName.getText();
            if (molname.contains("pubchem")) {
                String[] array = MolName.getText().split(": ");
                molname = array[1];
                MolName.setText(molname);

            }
            molname = MolName.getText();
        }

//psi_molcomment = PsiDescription.getText();
//cores  = Cores.getText();
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="PsiCubeRange [psi_cuberange] ">
        String cuberange = PsiCubeRange.getText();
        String num_cube = null;

        if (cuberange.contains("ALL")) {
            num_cube = "";
        } else {
            cuberange = cuberange.replaceAll(",\\s", ","); //prevent space in the string
            // parse fields for simple orbital list
            List<String> cuberange_arrA = new ArrayList<>(Arrays.asList(cuberange.split("[,|\\s|;]")));  // array_list instead of fixed array
            int cubeCount = cuberange_arrA.size();
            //log("---> Here's the entered list :" + cuberange_arrA);

            for (int j = 0; j < cubeCount; j++) {
                String cube_range = cuberange_arrA.get(j);

                // Parse ranges as strings are fed in
                if (cube_range.matches("[0-9]{1,3}-[0-9]{1,3}")) {
                    String[] cube_range_split = cube_range.split("[-]");
                    int lowIdx = Integer.parseInt(cube_range_split[0]);
                    int highIdx = Integer.parseInt(cube_range_split[1]);
                    List<Integer> num_range = new ArrayList<>(); // Get range into arraylist
                    for (int i = lowIdx; i <= highIdx; i++) {
                        num_range.add(i);
                    }
                    // Debug
                    //log("num_range int range listarray :" + num_range);
                    //log("===================");
                    // End of debug

                    // Integer list from ranges to String
                    List<String> num_range_str = Arrays.asList(num_range.toString().replaceAll("\\[(.*)\\]", "$1").split(", "));

                    // Append range values to other values in cuberange_arrA (merge lists)
                    num_range_str.forEach((s) -> {
                        cuberange_arrA.add(s);
                    }); // log("Printing combined array as strings: " + cuberange_arrA);
                } // End of if for ranges
            } // End of for loop

            // Remove strings of ranges e.g. "1-5"
            List<String> results = getMatchingStrings(cuberange_arrA, "[0-9]{1,3}-[0-9]{1,3}"); // Call method getMatchingStrings
            for (int m = 0; m < results.size(); m++) {
                cuberange_arrA.remove(results.get(m));
            }

            String[] num_cube_arr = cuberange_arrA.toArray(new String[cubeCount]); // List to array for later use
            int actCount = num_cube_arr.length;
            // Pass to output
           

            for (int i = 0; i < actCount; i++) {
                num_cube += num_cube_arr[i] + ",";
            }
            num_cube = num_cube.replaceAll("null", "").replaceAll(",$", "");
            //log(num_cube);
        }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Cube Properties">
//'density', 'orbitals', 'ESP', 'ELF'
        String CubeProp = "";
        if (PsiCubeDen.isSelected()) {
            CubeProp = "'density', ";
            psi_xyz = "YES";
        }
        if (PsiCubeOrb.isSelected()) {
            CubeProp = CubeProp + "'orbitals', ";
            psi_xyz = "YES";
        }
        if (PsiCubeEsp.isSelected()) {
            CubeProp = CubeProp + "'esp', ";
            psi_xyz = "YES";
        }
        if (PsiCubeElf.isSelected()) {
            CubeProp = CubeProp + "'elf'";
            psi_xyz = "YES";
        }
        if (PsiCubeDua.isSelected()) {
            CubeProp = CubeProp + "'DUAL_DESCRIPTOR'";
            psi_xyz = "YES";
        }
        if (!PsiCubeOrb.isSelected()) {
            num_cube = "";
        }
        CubeProp = CubeProp.replaceAll("null", "").replaceAll(", $", "");
        //log(CubeProp);
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Other variables">
        mwfn_path = PMwfn.getText();
        
        if (UseConstrains.isSelected()) {
            useconstrains = "YES";
        } else {
            useconstrains = "NO";
        }
// </editor-fold>


// Check for .macro folder / create it
//user_home = System.getProperty("user.home");
//createDirectoryIfNeeded(user_home + "/.jmol/macros");
//String jmol_path = user_home + "\\.jmol\\macros";
//log(jmol_path);

// Create an object first.
        Psi psi_main = new Psi();
        try {
            psi_conf = psi_main.Inputa(psi_pyapi, file_name, suff, inp_dir, memory, mybas, cores, molname, psi_method, psi_funct, psi_point, opt_type, psi_molcomment, psi_charge, psi_multi, psi_geom, psi_pubchem, psi_call, set_alone, link2, ingeo1, ingeo2, psi_freeze, psi_bas, psi_ref, psi_scftype, psi_puream, psi_natorb, psi_print, psi_prmos, psi_prbasis, psi_moldenout, psi_47out, psi_fchkout, psi_gdma, psi_xyz, addoptions, addrunopt, num_cube, CubeProp, psi_prop, psi_solvent, psi_local, mwfn_path, write47, writewfx, writemol2, psi_sapt, psi_cp, psi_ther, set_univ, opt_freq, resprop, psi_irc, jmol_path, psi_jmolloc, extension, king_options, useconstrains);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Save values into XML file
        saveParamChangesAsXML();

// Error checking
// <editor-fold defaultstate="collapsed" desc="Errors">
        if (PsiGeom.getText()
                .length() < 1 && PubChem.getText().length() < 1) {
            new Alert(Alert.AlertType.WARNING, "Molecular coordinates or PubChem ID are missing.\nEnter either one of them.\n", ButtonType.OK).showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }

        if (PsiCubeOrb.isSelected()
                && !PsiCubeRange.getText().contains("ALL") && PsiCubeRange.getText().length() < 1) {
            new Alert(Alert.AlertType.ERROR, "Enter orbital number to diplay.\nUse the 'CubeOrbitals' box.\n").showAndWait();
            return;
        }

        if (psi_solvent.length()
                > 1 && psi_point.length() < 1) {
            new Alert(Alert.AlertType.ERROR, "Choose point group symmetry. C1 was set by default.\n").showAndWait();
        }
        if ("YES".equals(psi_sapt) && !PsiGeom.getText().contains("--")) {
            new Alert(Alert.AlertType.ERROR, "There must be two molecules defined separated by '--'.\n").showAndWait();
            return;
        }
//        if (!"YES".equals(psi_sapt) && PsiGeom.getText().contains("--") && !psi_method.contains("SAPT")) {
//            new Alert(Alert.AlertType.WARNING, "For molecular interactions, choose one of the SAPT methods.\n");
//         return;
//        }
//        if (PsiSapt.isSelected() && !"SAPT-typical".equals(AddOptions.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Select method and choose from *More Options menu below.\nOK to save the file.\n", ButtonType.OK);
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.YES){
//            return;             
//            // ... user chose YES
//                }
//            return;
//        }
// </editor-fold>      

// Confirmation message
// <editor-fold defaultstate="collapsed" desc="confirmation">       
        String message;
        if (psi_conf
                == true) {
            message = "\nPSI4 input file " + file_name + suff + "." + extension +" \nwas created in directory: \n" + inp_dir;
            new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
            //PsiGeom.;
        } else {
            message = "PSI4 input file was NOT created. Check directory: \n" + inp_dir;
            new Alert(Alert.AlertType.ERROR, message).showAndWait();
            //PsiGeom.requestFocusInWindow();
        }
        // Create directory 'cubes'
//        if (FileExists.DirExists(inp_dir + File.separator + "cubes") == false) {
//            new File(inp_dir + File.separator + "cubes").mkdirs();
//            String messagecube = "Directory ./cubes was created.\n";
//            AreaOut.appendText(messagecube);
//            new Alert(Alert.AlertType.INFORMATION, messagecube).showAndWait();
//        }

    }
//</editor-fold>

    @FXML
    private void ClearAction(ActionEvent event) {
        boolean state = false;

        PsiGeom.setText("");
        PubChem.setText("");
        MolName.setText("");
        SetOptions.setText("");
        PsiFreq.setSelected(state);
        PsiTs.setSelected(state);
        PsiIrc.setSelected(state);
//        psi_geom = "";
//        molname = "";
    }

    @FXML
    private void ExitAction(ActionEvent event) throws Exception {
        System.exit(0); //Kills Runtime
        setVisible(false);
        dispose();
    }

    @FXML // Load file to process
    private void openOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            InpFile.setText("No File selected");
        } else {
            InpFile.setText(selectedFile.getAbsolutePath());
            String inp_file = selectedFile.getAbsolutePath();  //Change path into string
            inp_file = inp_file.replace("\\", "/");
        }
    }

    @FXML
    private void linkMouseClicked(ActionEvent event) {
        if (event.getSource() == link) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI("https://github.com/marpat/PSI4Helper");
                    desktop.browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    // do nothing
                }
            } else {
                //do nothing
            }

        }
    }

    //  methods
    // Check if .macro exists and create it if not
    private void createDirectoryIfNeeded(String directoryName) {
        File theDir = new File(directoryName);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            new Alert(Alert.AlertType.WARNING, "Directory /macros did not found. Attemped to make it.\n", ButtonType.OK).showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }
        theDir.mkdir();
    }

    public static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }



}
