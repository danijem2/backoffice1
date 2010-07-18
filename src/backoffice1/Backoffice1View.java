/*
 * Backoffice1View.java
 */

package backoffice1;


import gmp.swing.MutableList;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.awt.*;
import gmp.net.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import java.net.URL;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import gmp.host.net.*;
import gmp.terminal.local.*;
import gmp.net.*;
import gmp.swing.PhotoOperation;

/**
 * The application's main frame.
 */
public class Backoffice1View extends FrameView {

    File file = null;

    public static final String PhotoBOStorage = "C:\\Program Files\\Apache Software Foundation\\Apache2.2\\htdocs\\gmp\\photo\\backoffice\\";
    public static final String PhotoBOURL = "http://localhost/gmp/photo/backoffice/";
public static final String sexes[]  = { "M","F"};



H2HClient h2h = null;

    public Backoffice1View(SingleFrameApplication app) {
        super(app);

        initComponents();
        CommDevice c = new CommDevice( new gmprow("id=1&name=FlatRateInternet&transport=IP&stack=none&primary=true&auto=true&timeout=45&attempts=3&"));
        Server s = new Server( new gmprow("id=2&url=localhost&name=localtest&")) ;
        HttpProtocol http = new HttpProtocol("http");
        h2h = new H2HClient(c,s,http);


        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Backoffice1App.getApplication().getMainFrame();
            aboutBox = new Backoffice1AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Backoffice1App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanelAddWallet = new javax.swing.JPanel();
        jLabelFacepayId = new javax.swing.JLabel();
        jLabelCountry = new javax.swing.JLabel();
        jTextFieldFacepayId = new javax.swing.JTextField();
        jTextFieldCountry = new javax.swing.JTextField();
        jLabelNickname = new javax.swing.JLabel();
        jLabelSex = new javax.swing.JLabel();
        jLabelFirstname = new javax.swing.JLabel();
        jLabelLastname = new javax.swing.JLabel();
        jLabelBirthyear = new javax.swing.JLabel();
        jTextFieldNickname = new javax.swing.JTextField();
        jTextFieldFirstname = new javax.swing.JTextField();
        jTextFieldLastname = new javax.swing.JTextField();
        jTextFieldBirtyear = new javax.swing.JTextField();
        jLabelWalletPublicCode = new javax.swing.JLabel();
        jTextFieldWalletPublicCode = new javax.swing.JTextField();
        jButtonUploadPhotography = new javax.swing.JButton();
        jLabelPhoto = new javax.swing.JLabel();
        jLabelPhotoCaption = new javax.swing.JLabel();
        jButtonApproveWallet = new javax.swing.JButton();
        jLabelMyMail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAddWalletResponse = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSex = new javax.swing.JList();
        jPanelAddAccount = new javax.swing.JPanel();
        jLabelAccountPublicCode = new javax.swing.JLabel();
        jLabelExpiryDate = new javax.swing.JLabel();
        jLabelExternalAccount = new javax.swing.JLabel();
        jLabelInstitution = new javax.swing.JLabel();
        jTextFieldAccountPublicCode = new javax.swing.JTextField();
        jTextFieldExpirationDate = new javax.swing.JTextField();
        jTextFieldExternalAccountId = new javax.swing.JTextField();
        jTextFieldInstitution = new javax.swing.JTextField();
        jButtonApproveAccount = new javax.swing.JButton();
        jLabelAddAccountWalletPublicCode = new javax.swing.JLabel();
        jTextFieldAddAccountWalletPublicCode = new javax.swing.JTextField();
        jScrollPaneAddAccount = new javax.swing.JScrollPane();
        jTextAreaAddAccount = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelAddAccountFrendlyName = new javax.swing.JLabel();
        jTextFieldAddAccountFrendlyname = new javax.swing.JTextField();
        jLabelApplicationPhoto = new javax.swing.JLabel();
        jLabelApplicationPhotoCaption = new javax.swing.JLabel();
        jButtonUploadApplicationPhoto = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jIssuingMenu = new javax.swing.JMenu();
        jMenuIssuingNew = new javax.swing.JMenu();
        jMenuItemAddWallet = new javax.swing.JMenuItem();
        jMenuItemAddAccount = new javax.swing.JMenuItem();
        jMenuAddDocument = new javax.swing.JMenuItem();
        jMenuAcquiring = new javax.swing.JMenu();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.CardLayout());

        jPanelAddWallet.setName("jPanelAddWallet"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(backoffice1.Backoffice1App.class).getContext().getResourceMap(Backoffice1View.class);
        jLabelFacepayId.setText(resourceMap.getString("jLabelFacepayId.text")); // NOI18N
        jLabelFacepayId.setName("jLabelFacepayId"); // NOI18N

        jLabelCountry.setText(resourceMap.getString("jLabelCountry.text")); // NOI18N
        jLabelCountry.setName("jLabelCountry"); // NOI18N

        jTextFieldFacepayId.setText(resourceMap.getString("jTextFieldFacepayId.text")); // NOI18N
        jTextFieldFacepayId.setName("jTextFieldFacepayId"); // NOI18N

        jTextFieldCountry.setText(resourceMap.getString("jTextFieldCountry.text")); // NOI18N
        jTextFieldCountry.setName("jTextFieldCountry"); // NOI18N

        jLabelNickname.setText(resourceMap.getString("jLabelNickname.text")); // NOI18N
        jLabelNickname.setName("jLabelNickname"); // NOI18N

        jLabelSex.setText(resourceMap.getString("jLabelSex.text")); // NOI18N
        jLabelSex.setName("jLabelSex"); // NOI18N

        jLabelFirstname.setText(resourceMap.getString("jLabelFirstname.text")); // NOI18N
        jLabelFirstname.setName("jLabelFirstname"); // NOI18N

        jLabelLastname.setText(resourceMap.getString("jLabelLastname.text")); // NOI18N
        jLabelLastname.setName("jLabelLastname"); // NOI18N

        jLabelBirthyear.setText(resourceMap.getString("jLabelBirthyear.text")); // NOI18N
        jLabelBirthyear.setName("jLabelBirthyear"); // NOI18N

        jTextFieldNickname.setName("jTextFieldNickname"); // NOI18N

        jTextFieldFirstname.setName("jTextFieldFirstname"); // NOI18N

        jTextFieldLastname.setName("jTextFieldLastname"); // NOI18N

        jTextFieldBirtyear.setName("jTextFieldBirtyear"); // NOI18N

        jLabelWalletPublicCode.setText(resourceMap.getString("jLabelWalletPublicCode.text")); // NOI18N
        jLabelWalletPublicCode.setName("jLabelWalletPublicCode"); // NOI18N

        jTextFieldWalletPublicCode.setName("jTextFieldWalletPublicCode"); // NOI18N

        jButtonUploadPhotography.setText(resourceMap.getString("jButtonUploadPhotography.text")); // NOI18N
        jButtonUploadPhotography.setName("jButtonUploadPhotography"); // NOI18N
        jButtonUploadPhotography.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUploadPhotographyMouseClicked(evt);
            }
        });

        jLabelPhoto.setText(resourceMap.getString("jLabelPhoto.text")); // NOI18N
        jLabelPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelPhoto.setName("jLabelPhoto"); // NOI18N

        jLabelPhotoCaption.setText(resourceMap.getString("jLabelPhotoCaption.text")); // NOI18N
        jLabelPhotoCaption.setName("jLabelPhotoCaption"); // NOI18N

        jButtonApproveWallet.setForeground(resourceMap.getColor("jButtonApproveWallet.foreground")); // NOI18N
        jButtonApproveWallet.setText(resourceMap.getString("jButtonApproveWallet.text")); // NOI18N
        jButtonApproveWallet.setName("jButtonApproveWallet"); // NOI18N
        jButtonApproveWallet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonApproveWalletMouseClicked(evt);
            }
        });

        jLabelMyMail.setText(resourceMap.getString("jLabelMyMail.text")); // NOI18N
        jLabelMyMail.setName("jLabelMyMail"); // NOI18N

        jTextFieldEmail.setName("jTextFieldEmail"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextAreaAddWalletResponse.setColumns(20);
        jTextAreaAddWalletResponse.setRows(5);
        jTextAreaAddWalletResponse.setName("jTextAreaAddWalletResponse"); // NOI18N
        jScrollPane1.setViewportView(jTextAreaAddWalletResponse);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jListSex.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "M", "F" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListSex.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSex.setMaximumSize(new java.awt.Dimension(12, 6));
        jListSex.setMinimumSize(new java.awt.Dimension(12, 6));
        jListSex.setName("jListSex"); // NOI18N
        jListSex.setPreferredSize(new java.awt.Dimension(12, 6));
        jListSex.setVisibleRowCount(2);
        jScrollPane2.setViewportView(jListSex);

        javax.swing.GroupLayout jPanelAddWalletLayout = new javax.swing.GroupLayout(jPanelAddWallet);
        jPanelAddWallet.setLayout(jPanelAddWalletLayout);
        jPanelAddWalletLayout.setHorizontalGroup(
            jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabelPhotoCaption))
                    .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                                .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddWalletLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                                .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabelFacepayId)
                                                    .addComponent(jLabelNickname)
                                                    .addComponent(jLabelCountry)
                                                    .addComponent(jLabelFirstname)
                                                    .addComponent(jLabelLastname)
                                                    .addComponent(jLabelSex))
                                                .addGap(38, 38, 38))
                                            .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabelWalletPublicCode)
                                                    .addComponent(jLabelMyMail)
                                                    .addComponent(jLabelBirthyear))
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldFacepayId, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldNickname, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldLastname, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldBirtyear, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldCountry, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldWalletPublicCode, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddWalletLayout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButtonApproveWallet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAddWalletLayout.createSequentialGroup()
                                            .addGap(42, 42, 42)
                                            .addComponent(jButtonUploadPhotography))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanelAddWalletLayout.setVerticalGroup(
            jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                .addComponent(jLabelPhotoCaption)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFacepayId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFacepayId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCountry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNickname)
                            .addComponent(jTextFieldNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSex)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddWalletLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelFirstname))
                            .addComponent(jTextFieldFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLastname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldBirtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBirthyear))
                        .addGap(13, 13, 13)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelWalletPublicCode)
                            .addComponent(jTextFieldWalletPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAddWalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMyMail)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUploadPhotography)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonApproveWallet, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        mainPanel.add(jPanelAddWallet, "AddWallet");

        jPanelAddAccount.setName("jPanelAddAccount"); // NOI18N

        jLabelAccountPublicCode.setText(resourceMap.getString("jLabelAccountPublicCode.text")); // NOI18N
        jLabelAccountPublicCode.setName("jLabelAccountPublicCode"); // NOI18N

        jLabelExpiryDate.setText(resourceMap.getString("jLabelExpiryDate.text")); // NOI18N
        jLabelExpiryDate.setName("jLabelExpiryDate"); // NOI18N

        jLabelExternalAccount.setText(resourceMap.getString("jLabelExternalAccount.text")); // NOI18N
        jLabelExternalAccount.setName("jLabelExternalAccount"); // NOI18N

        jLabelInstitution.setText(resourceMap.getString("jLabelInstitution.text")); // NOI18N
        jLabelInstitution.setName("jLabelInstitution"); // NOI18N

        jTextFieldAccountPublicCode.setText(resourceMap.getString("jTextFieldAccountPublicCode.text")); // NOI18N
        jTextFieldAccountPublicCode.setName("jTextFieldAccountPublicCode"); // NOI18N

        jTextFieldExpirationDate.setName("jTextFieldExpirationDate"); // NOI18N

        jTextFieldExternalAccountId.setName("jTextFieldExternalAccountId"); // NOI18N

        jTextFieldInstitution.setName("jTextFieldInstitution"); // NOI18N

        jButtonApproveAccount.setForeground(resourceMap.getColor("jButtonApproveAccount.foreground")); // NOI18N
        jButtonApproveAccount.setText(resourceMap.getString("jButtonApproveAccount.text")); // NOI18N
        jButtonApproveAccount.setName("jButtonApproveAccount"); // NOI18N
        jButtonApproveAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonApproveAccountMouseClicked(evt);
            }
        });

        jLabelAddAccountWalletPublicCode.setText(resourceMap.getString("jLabelAddAccountWalletPublicCode.text")); // NOI18N
        jLabelAddAccountWalletPublicCode.setName("jLabelAddAccountWalletPublicCode"); // NOI18N

        jTextFieldAddAccountWalletPublicCode.setName("jTextFieldAddAccountWalletPublicCode"); // NOI18N

        jScrollPaneAddAccount.setName("jScrollPaneAddAccount"); // NOI18N

        jTextAreaAddAccount.setColumns(20);
        jTextAreaAddAccount.setRows(5);
        jTextAreaAddAccount.setName("jTextAreaAddAccount"); // NOI18N
        jScrollPaneAddAccount.setViewportView(jTextAreaAddAccount);

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        jLabelAddAccountFrendlyName.setText(resourceMap.getString("jLabelAddAccountFrendlyName.text")); // NOI18N
        jLabelAddAccountFrendlyName.setName("jLabelAddAccountFrendlyName"); // NOI18N

        jTextFieldAddAccountFrendlyname.setName("jTextFieldAddAccountFrendlyname"); // NOI18N

        jLabelApplicationPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelApplicationPhoto.setText(resourceMap.getString("jLabelApplicationPhoto.text")); // NOI18N
        jLabelApplicationPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelApplicationPhoto.setName("jLabelApplicationPhoto"); // NOI18N

        jLabelApplicationPhotoCaption.setText(resourceMap.getString("jLabelApplicationPhotoCaption.text")); // NOI18N
        jLabelApplicationPhotoCaption.setName("jLabelApplicationPhotoCaption"); // NOI18N

        jButtonUploadApplicationPhoto.setText(resourceMap.getString("jButtonUploadApplicationPhoto.text")); // NOI18N
        jButtonUploadApplicationPhoto.setName("jButtonUploadApplicationPhoto"); // NOI18N
        jButtonUploadApplicationPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUploadApplicationPhotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddAccountLayout = new javax.swing.GroupLayout(jPanelAddAccount);
        jPanelAddAccount.setLayout(jPanelAddAccountLayout);
        jPanelAddAccountLayout.setHorizontalGroup(
            jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExpiryDate)
                                    .addComponent(jLabelAccountPublicCode)
                                    .addComponent(jLabelInstitution))
                                .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldAccountPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addComponent(jLabelAddAccountWalletPublicCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldAddAccountWalletPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addComponent(jLabelExternalAccount)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldExternalAccountId, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAddAccountLayout.createSequentialGroup()
                                    .addComponent(jCheckBox1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonUploadApplicationPhoto))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAddAccountLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabelAddAccountFrendlyName)
                                    .addGap(28, 28, 28)
                                    .addComponent(jTextFieldAddAccountFrendlyname, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButtonApproveAccount)))
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabelApplicationPhotoCaption))
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelApplicationPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPaneAddAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanelAddAccountLayout.setVerticalGroup(
            jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                        .addComponent(jLabelApplicationPhotoCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelApplicationPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAccountPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAccountPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelExpiryDate)
                            .addComponent(jTextFieldExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelExternalAccount)
                            .addComponent(jTextFieldExternalAccountId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelInstitution)
                            .addComponent(jTextFieldInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAddAccountWalletPublicCode)
                            .addGroup(jPanelAddAccountLayout.createSequentialGroup()
                                .addComponent(jTextFieldAddAccountWalletPublicCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldAddAccountFrendlyname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAddAccountFrendlyName))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAddAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jButtonUploadApplicationPhoto))
                        .addGap(37, 37, 37)
                        .addComponent(jButtonApproveAccount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneAddAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        mainPanel.add(jPanelAddAccount, "AddAccount");

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(backoffice1.Backoffice1App.class).getContext().getActionMap(Backoffice1View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jIssuingMenu.setText(resourceMap.getString("jIssuingMenu.text")); // NOI18N
        jIssuingMenu.setName("jIssuingMenu"); // NOI18N

        jMenuIssuingNew.setText(resourceMap.getString("jMenuIssuingNew.text")); // NOI18N
        jMenuIssuingNew.setName("jMenuIssuingNew"); // NOI18N

        jMenuItemAddWallet.setText(resourceMap.getString("jMenuItemAddWallet.text")); // NOI18N
        jMenuItemAddWallet.setName("jMenuItemAddWallet"); // NOI18N
        jMenuItemAddWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddWalletActionPerformed(evt);
            }
        });
        jMenuIssuingNew.add(jMenuItemAddWallet);

        jMenuItemAddAccount.setText(resourceMap.getString("jMenuItemAddAccount.text")); // NOI18N
        jMenuItemAddAccount.setName("jMenuItemAddAccount"); // NOI18N
        jMenuItemAddAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddAccountActionPerformed(evt);
            }
        });
        jMenuIssuingNew.add(jMenuItemAddAccount);

        jMenuAddDocument.setText(resourceMap.getString("jMenuAddDocument.text")); // NOI18N
        jMenuAddDocument.setName("jMenuAddDocument"); // NOI18N
        jMenuIssuingNew.add(jMenuAddDocument);

        jIssuingMenu.add(jMenuIssuingNew);

        menuBar.add(jIssuingMenu);

        jMenuAcquiring.setText(resourceMap.getString("jMenuAcquiring.text")); // NOI18N
        jMenuAcquiring.setName("jMenuAcquiring"); // NOI18N
        menuBar.add(jMenuAcquiring);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 653, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUploadPhotographyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUploadPhotographyMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("Pick the photo");
        fileChooser.setFileFilter(new ExtensionFileFilter("JPG"));
        int returnVal = fileChooser.showOpenDialog(this.getFrame());
    if (returnVal == JFileChooser.APPROVE_OPTION) {
       file = fileChooser.getSelectedFile();
       System.out.println("File picked :"+file.getName());
       try {
         BufferedImage image = ImageIO.read(file);
         File out = new File(PhotoBOStorage+file.getName());
         PhotoOperation.writeImageToJPG(out, image);
         System.out.println("write image OK to File"+ out.getAbsolutePath());
         this.jLabelPhoto.setIcon(new ImageIcon(image));

         }
       catch (IOException ex) {
         this.jLabelPhoto.setIcon(idleIcon);
         Logger.getLogger(Backoffice1View.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
    else {
        System.out.println("File access cancelled by user.");
       }

    }//GEN-LAST:event_jButtonUploadPhotographyMouseClicked

    private void jButtonApproveWalletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonApproveWalletMouseClicked
        // TODO add your handling code here:

int  MessageType ;
String  Timestamp ;
String FacepayId;
String Docid;
String Country;
String Person;
String Sex;
String Nickname;
String Firstname;
String Lastname;
String Birthyear;
String Photoname;
String WalletId;
String WalletPublicCode;
String Language;
String SWversion;
String Bluetooth ;
String NFC;
String Sound;
String GPRS ;
String SMS;
String MyMail;
String Model;
    gmprow request = null;
      String tmp = "";
      MessageType = MessageTypes.NEW_FACEPAY_ID;
        tmp+="MessageType"+gmptlv.eqsign+MessageType+gmptlv.delimiter ;
    try{
      Timestamp = gmpconverter.now();
       tmp+="Timestamp"+gmptlv.eqsign+Timestamp+gmptlv.delimiter ;
      FacepayId = this.jTextFieldFacepayId.getText();
        tmp+="FacepayId"+gmptlv.eqsign+FacepayId+gmptlv.delimiter ;
      Docid = FacepayId;
        tmp+="Docid"+gmptlv.eqsign+Docid+gmptlv.delimiter ;
      Country = this.jTextFieldCountry.getText();
        tmp+="Country"+gmptlv.eqsign+Country+gmptlv.delimiter ;
      Person = FacepayId;
        tmp+="Person"+gmptlv.eqsign+Person+gmptlv.delimiter ;
      Sex = this.sexes[this.jListSex.getSelectedIndex()];
        tmp+="Sex"+gmptlv.eqsign+Sex+gmptlv.delimiter ;
      Nickname = this.jTextFieldNickname.getText();
        tmp+="Nickname"+gmptlv.eqsign+Nickname+gmptlv.delimiter ;
      Firstname = this.jTextFieldFirstname.getText();
        tmp+="Firstname"+gmptlv.eqsign+Firstname+gmptlv.delimiter ;
      Lastname = this.jTextFieldLastname.getText();
        tmp+="Lastname"+gmptlv.eqsign+Lastname+gmptlv.delimiter ;
      Birthyear = this.jTextFieldBirtyear.getText();
        tmp+="Birthyear"+gmptlv.eqsign+Birthyear+gmptlv.delimiter ;
      Photoname = this.PhotoBOURL+file.getName();
        tmp+="Photoname"+gmptlv.eqsign+Photoname+gmptlv.delimiter ;
      WalletId = FacepayId;
        tmp+="WalletId"+gmptlv.eqsign+WalletId+gmptlv.delimiter ;
      WalletPublicCode = this.jTextFieldWalletPublicCode.getText();
        tmp+="WalletPublicCode"+gmptlv.eqsign+WalletPublicCode+gmptlv.delimiter ;
      Language = Country; // 99% of cases
        tmp+="Language"+gmptlv.eqsign+Language+gmptlv.delimiter ;
      SWversion = "1.0";
        tmp+="SWversion"+gmptlv.eqsign+SWversion+gmptlv.delimiter ;
      Bluetooth = "Y";
        tmp+="Bluetooth"+gmptlv.eqsign+Bluetooth+gmptlv.delimiter ;
      NFC = "N";
        tmp+="NFC"+gmptlv.eqsign+NFC+gmptlv.delimiter ;
      Sound = "Y";
        tmp+="Sound"+gmptlv.eqsign+Sound+gmptlv.delimiter ;
      GPRS = "Y";
        tmp+="GPRS"+gmptlv.eqsign+GPRS+gmptlv.delimiter ;
      SMS = "N";
        tmp+="SMS"+gmptlv.eqsign+SMS+gmptlv.delimiter ;
      MyMail = this.jTextFieldEmail.getText();
        tmp+="MyMail"+gmptlv.eqsign+MyMail+gmptlv.delimiter ;
      Model = "Nokia";
        tmp+="Model"+gmptlv.eqsign+Model+gmptlv.delimiter ;

        request = new gmprow(tmp);
      }
    catch(Exception ex){
        request = null;
    }
        try {
            Request req = new Request(MessageType, request);
            req.setServiceScript("terminalfront/H4H");
            ResponseSwing resp = this.h2h.Send(req);
            this.jTextAreaAddWalletResponse.append(resp.getRawData()+"\n");
        } catch (IOException ex) {
          gmpplatform.Log("IOException sending req"+ex.getMessage());
          gmpplatform.Log("req"+request.write());
        }


    }//GEN-LAST:event_jButtonApproveWalletMouseClicked

    private void jMenuItemAddWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddWalletActionPerformed
        // TODO add your handling code here:
         ((CardLayout)(mainPanel.getLayout())).show(mainPanel, "AddWallet");
    }//GEN-LAST:event_jMenuItemAddWalletActionPerformed

    private void jMenuItemAddAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddAccountActionPerformed
        // TODO add your handling code here:
        ((CardLayout)(mainPanel.getLayout())).show(mainPanel, "AddAccount");
    }//GEN-LAST:event_jMenuItemAddAccountActionPerformed

    private void jButtonApproveAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonApproveAccountMouseClicked
        // TODO add your handling code here:
   int MessageType;
   String  Timestamp ;
   String WalletPublicCode = null;
   String  id = null;
   String institution = null;
   boolean anycommission = false;
   String ExternalAccId = null;
   String AccountPublicCode = null;
   String expiry= null;
   String frendlyname = null;
   String Photoname = null;

   gmprow request = null;
   String tmp = "";
   MessageType = MessageTypes.NEW_ACCOUNT;
      tmp+="MessageType"+gmptlv.eqsign+MessageType+gmptlv.delimiter ;
    try{   
      Timestamp = gmpconverter.now();
       tmp+="Timestamp"+gmptlv.eqsign+Timestamp+gmptlv.delimiter ;
      expiry = this.jTextFieldExpirationDate.getText();
        tmp+="expiry"+gmptlv.eqsign+expiry+gmptlv.delimiter ;
      WalletPublicCode = this.jTextFieldAddAccountWalletPublicCode.getText();
        tmp+="WalletPublicCode"+gmptlv.eqsign+WalletPublicCode+gmptlv.delimiter ;
      AccountPublicCode = this.jTextFieldAccountPublicCode.getText();
        tmp+="AccountPublicCode"+gmptlv.eqsign+AccountPublicCode+gmptlv.delimiter ;
      ExternalAccId = this.jTextFieldExternalAccountId.getText();
        tmp+="ExternalAccId"+gmptlv.eqsign+ExternalAccId+gmptlv.delimiter ;
      institution = this.jTextFieldInstitution.getText();
        tmp+="institution"+gmptlv.eqsign+institution+gmptlv.delimiter ;
      Photoname = this.PhotoBOURL+this.file.getName();
        tmp+="Photoname"+gmptlv.eqsign+Photoname+gmptlv.delimiter ;
      frendlyname = this.jTextFieldAddAccountFrendlyname.getText();
        tmp+="frendlyname"+gmptlv.eqsign+frendlyname+gmptlv.delimiter ;
      id =  AccountPublicCode ; // Temporary !!!!!
        tmp+="id"+gmptlv.eqsign+id+gmptlv.delimiter ;
     anycommission = this.jCheckBox1.isSelected();
        tmp+="anycommission"+gmptlv.eqsign+anycommission+gmptlv.delimiter ;
      request = new gmprow(tmp);
      }
    catch(Exception ex){
        request = null;
    }
        try {
            Request req = new Request(MessageType, request);
            req.setServiceScript("terminalfront/H4H");
            ResponseSwing resp = this.h2h.Send(req);
            this.jTextAreaAddAccount.append(resp.getRawData()+"\n");
        } catch (IOException ex) {
          gmpplatform.Log("IOException sending req"+ex.getMessage());
          gmpplatform.Log("req"+request.write());
        }

    }//GEN-LAST:event_jButtonApproveAccountMouseClicked

    private void jButtonUploadApplicationPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUploadApplicationPhotoMouseClicked
        // TODO add your handling code here:
         JFileChooser fileChooser = new JFileChooser("Pick the photo");
        fileChooser.setFileFilter(new ExtensionFileFilter("JPG"));
        int returnVal = fileChooser.showOpenDialog(this.getFrame());
    if (returnVal == JFileChooser.APPROVE_OPTION) {
       file = fileChooser.getSelectedFile();
       System.out.println("File picked :"+file.getName());
       try {
         BufferedImage image = ImageIO.read(file);
         File out = new File(PhotoBOStorage+file.getName());
         PhotoOperation.writeImageToJPG(out, image);
         System.out.println("write image OK to File"+ out.getAbsolutePath()+out.getName());
         this.jLabelApplicationPhoto.setIcon(new ImageIcon(image));
         }
       catch (IOException ex) {
         this.jLabelApplicationPhoto.setIcon(idleIcon);
         Logger.getLogger(Backoffice1View.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
    else {
        System.out.println("File access cancelled by user.");
       }

    }//GEN-LAST:event_jButtonUploadApplicationPhotoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApproveAccount;
    private javax.swing.JButton jButtonApproveWallet;
    private javax.swing.JButton jButtonUploadApplicationPhoto;
    private javax.swing.JButton jButtonUploadPhotography;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenu jIssuingMenu;
    private javax.swing.JLabel jLabelAccountPublicCode;
    private javax.swing.JLabel jLabelAddAccountFrendlyName;
    private javax.swing.JLabel jLabelAddAccountWalletPublicCode;
    private javax.swing.JLabel jLabelApplicationPhoto;
    private javax.swing.JLabel jLabelApplicationPhotoCaption;
    private javax.swing.JLabel jLabelBirthyear;
    private javax.swing.JLabel jLabelCountry;
    private javax.swing.JLabel jLabelExpiryDate;
    private javax.swing.JLabel jLabelExternalAccount;
    private javax.swing.JLabel jLabelFacepayId;
    private javax.swing.JLabel jLabelFirstname;
    private javax.swing.JLabel jLabelInstitution;
    private javax.swing.JLabel jLabelLastname;
    private javax.swing.JLabel jLabelMyMail;
    private javax.swing.JLabel jLabelNickname;
    private javax.swing.JLabel jLabelPhoto;
    private javax.swing.JLabel jLabelPhotoCaption;
    private javax.swing.JLabel jLabelSex;
    private javax.swing.JLabel jLabelWalletPublicCode;
    private javax.swing.JList jListSex;
    private javax.swing.JMenu jMenuAcquiring;
    private javax.swing.JMenuItem jMenuAddDocument;
    private javax.swing.JMenu jMenuIssuingNew;
    private javax.swing.JMenuItem jMenuItemAddAccount;
    private javax.swing.JMenuItem jMenuItemAddWallet;
    private javax.swing.JPanel jPanelAddAccount;
    private javax.swing.JPanel jPanelAddWallet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneAddAccount;
    private javax.swing.JTextArea jTextAreaAddAccount;
    private javax.swing.JTextArea jTextAreaAddWalletResponse;
    private javax.swing.JTextField jTextFieldAccountPublicCode;
    private javax.swing.JTextField jTextFieldAddAccountFrendlyname;
    private javax.swing.JTextField jTextFieldAddAccountWalletPublicCode;
    private javax.swing.JTextField jTextFieldBirtyear;
    private javax.swing.JTextField jTextFieldCountry;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldExpirationDate;
    private javax.swing.JTextField jTextFieldExternalAccountId;
    private javax.swing.JTextField jTextFieldFacepayId;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldInstitution;
    private javax.swing.JTextField jTextFieldLastname;
    private javax.swing.JTextField jTextFieldNickname;
    private javax.swing.JTextField jTextFieldWalletPublicCode;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
