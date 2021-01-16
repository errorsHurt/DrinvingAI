/*
 * Created by JFormDesigner on Fri Jan 15 18:28:55 CET 2021
 */

package com.widenetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

/**
 * @author Maximilian Meier
 */
public class Gui extends JFrame {

    public static ImageProcessing ir = new ImageProcessing();
    public Gui() {
        initComponents();
    }


    private void sBorderButtonActionPerformed(ActionEvent e) {
        waitFor(2);
        ir.takeScreensBrd((int)borderSpinner.getValue(),10);
        ir.editImageBorderDirectory();
        System.out.println("Finished");
    }

    private void sVelocityButtonActionPerformed(ActionEvent e) {
        waitFor(2);
        ir.takeScreensVelo((int) velocitySpinner.getValue(),20);
        ir.ocrImageVeloDirectory();
        System.out.println("Finished");
    }

    private void runAllButtonActionPerformed(ActionEvent e) {
        waitFor(2);
        ir.takeScreensBoth((int) bothSpinner.getValue(), 30);
        ir.editImageBorderDirectory();
        ir.ocrImageVeloDirectory();
        System.out.println("Hello");
        System.out.println("Finished");
    }

    private void deletImagesButtonActionPerformed(ActionEvent e) {
        ir.deleteImagesB();
        System.out.println("All Images from track are deleted");
        ir.deleteImagesV();
        System.out.println("All Images from velocity are deleted");
    }
    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Maximilian Meier
        panel3 = new JPanel();
        panel1 = new JPanel();
        panel4 = new JPanel();
        hSpacer1 = new JPanel(null);
        panelContainer = new JPanel();
        scanBorderPanel = new JPanel();
        sBorderLablel = new JLabel();
        hSpacer2 = new JPanel(null);
        scanVelocityPanel = new JPanel();
        sVelocityLablel = new JLabel();
        hSpacer3 = new JPanel(null);
        runAllPanel = new JPanel();
        runAllLablel = new JLabel();
        hSpacer4 = new JPanel(null);
        panel8 = new JPanel();
        separator3 = new JSeparator();
        panel9 = new JPanel();
        borderSpinner = new JSpinner();
        sBorderButton = new JButton();
        separator6 = new JSeparator();
        separator2 = new JSeparator();
        panel10 = new JPanel();
        velocitySpinner = new JSpinner();
        sVelocityButton = new JButton();
        separator1 = new JSeparator();
        separator5 = new JSeparator();
        panel11 = new JPanel();
        bothSpinner = new JSpinner();
        runAllButton = new JButton();
        separator4 = new JSeparator();
        panel5 = new JPanel();
        hSpacer5 = new JPanel(null);
        deletImagesButton = new JButton();
        hSpacer6 = new JPanel(null);

        //======== this ========
        setMinimumSize(new Dimension(200, 150));
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== panel3 ========
        {
            panel3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel3. getBorder
            ( )) ); panel3. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

            //======== panel1 ========
            {
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            }
            panel3.add(panel1);

            //======== panel4 ========
            {
                panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

                //---- hSpacer1 ----
                hSpacer1.setMaximumSize(new Dimension(10000, 32767));
                panel4.add(hSpacer1);

                //======== panelContainer ========
                {
                    panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));

                    //======== scanBorderPanel ========
                    {
                        scanBorderPanel.setLayout(new BoxLayout(scanBorderPanel, BoxLayout.X_AXIS));

                        //---- sBorderLablel ----
                        sBorderLablel.setText("Scan border:");
                        scanBorderPanel.add(sBorderLablel);
                        scanBorderPanel.add(hSpacer2);
                    }
                    panelContainer.add(scanBorderPanel);

                    //======== scanVelocityPanel ========
                    {
                        scanVelocityPanel.setLayout(new BoxLayout(scanVelocityPanel, BoxLayout.X_AXIS));

                        //---- sVelocityLablel ----
                        sVelocityLablel.setText("Scan velocity:");
                        scanVelocityPanel.add(sVelocityLablel);
                        scanVelocityPanel.add(hSpacer3);
                    }
                    panelContainer.add(scanVelocityPanel);

                    //======== runAllPanel ========
                    {
                        runAllPanel.setLayout(new BoxLayout(runAllPanel, BoxLayout.X_AXIS));

                        //---- runAllLablel ----
                        runAllLablel.setText("Run all:");
                        runAllPanel.add(runAllLablel);
                        runAllPanel.add(hSpacer4);
                    }
                    panelContainer.add(runAllPanel);
                }
                panel4.add(panelContainer);

                //======== panel8 ========
                {
                    panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));
                    panel8.add(separator3);

                    //======== panel9 ========
                    {
                        panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));

                        //---- borderSpinner ----
                        borderSpinner.setMinimumSize(new Dimension(120, 30));
                        borderSpinner.setMaximumSize(new Dimension(120, 30));
                        borderSpinner.setPreferredSize(new Dimension(120, 30));
                        panel9.add(borderSpinner);

                        //---- sBorderButton ----
                        sBorderButton.setText("Run");
                        sBorderButton.setMinimumSize(new Dimension(130, 30));
                        sBorderButton.setMaximumSize(new Dimension(130, 30));
                        sBorderButton.addActionListener(e -> sBorderButtonActionPerformed(e));
                        panel9.add(sBorderButton);
                    }
                    panel8.add(panel9);
                    panel8.add(separator6);
                    panel8.add(separator2);

                    //======== panel10 ========
                    {
                        panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));

                        //---- velocitySpinner ----
                        velocitySpinner.setMaximumSize(new Dimension(120, 30));
                        velocitySpinner.setMinimumSize(new Dimension(120, 30));
                        velocitySpinner.setPreferredSize(new Dimension(120, 30));
                        panel10.add(velocitySpinner);

                        //---- sVelocityButton ----
                        sVelocityButton.setText("Run");
                        sVelocityButton.setMinimumSize(new Dimension(130, 30));
                        sVelocityButton.setMaximumSize(new Dimension(130, 30));
                        sVelocityButton.addActionListener(e -> sVelocityButtonActionPerformed(e));
                        panel10.add(sVelocityButton);
                    }
                    panel8.add(panel10);
                    panel8.add(separator1);
                    panel8.add(separator5);

                    //======== panel11 ========
                    {
                        panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));

                        //---- bothSpinner ----
                        bothSpinner.setMaximumSize(new Dimension(120, 30));
                        bothSpinner.setMinimumSize(new Dimension(120, 30));
                        bothSpinner.setPreferredSize(new Dimension(120, 30));
                        panel11.add(bothSpinner);

                        //---- runAllButton ----
                        runAllButton.setText("Run");
                        runAllButton.setMinimumSize(new Dimension(130, 30));
                        runAllButton.setMaximumSize(new Dimension(130, 30));
                        runAllButton.addActionListener(e -> runAllButtonActionPerformed(e));
                        panel11.add(runAllButton);
                    }
                    panel8.add(panel11);
                    panel8.add(separator4);
                }
                panel4.add(panel8);
            }
            panel3.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
                panel5.add(hSpacer5);

                //---- deletImagesButton ----
                deletImagesButton.setText("Delete all images");
                deletImagesButton.addActionListener(e -> deletImagesButtonActionPerformed(e));
                panel5.add(deletImagesButton);
                panel5.add(hSpacer6);
            }
            panel3.add(panel5);
        }
        contentPane.add(panel3);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void waitFor(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Maximilian Meier
    private JPanel panel3;
    private JPanel panel1;
    private JPanel panel4;
    private JPanel hSpacer1;
    private JPanel panelContainer;
    private JPanel scanBorderPanel;
    private JLabel sBorderLablel;
    private JPanel hSpacer2;
    private JPanel scanVelocityPanel;
    private JLabel sVelocityLablel;
    private JPanel hSpacer3;
    private JPanel runAllPanel;
    private JLabel runAllLablel;
    private JPanel hSpacer4;
    private JPanel panel8;
    private JSeparator separator3;
    private JPanel panel9;
    private JSpinner borderSpinner;
    private JButton sBorderButton;
    private JSeparator separator6;
    private JSeparator separator2;
    private JPanel panel10;
    private JSpinner velocitySpinner;
    private JButton sVelocityButton;
    private JSeparator separator1;
    private JSeparator separator5;
    private JPanel panel11;
    private JSpinner bothSpinner;
    private JButton runAllButton;
    private JSeparator separator4;
    private JPanel panel5;
    private JPanel hSpacer5;
    private JButton deletImagesButton;
    private JPanel hSpacer6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
