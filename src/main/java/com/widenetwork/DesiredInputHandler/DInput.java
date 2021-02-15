/*
 * Created by JFormDesigner on Mon Feb 08 16:24:14 CET 2021
 */

package com.widenetwork.DesiredInputHandler;

import com.widenetwork.ImageEditing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Maximilian Meier
 */
public class DInput extends JFrame {

    public static ImageEditing iE = new ImageEditing();

    public DInput() {
        initComponents();
    }

    private void oneTrueBtnActionPerformed(ActionEvent e) {
        value1Label.setText("1");
    }

    private void oneFalseBtnActionPerformed(ActionEvent e) {
        value1Label.setText("0");
    }

    private void twoTrueBtnActionPerformed(ActionEvent e) {
        value2Label.setText("1");
    }

    private void twoFalseBtnActionPerformed(ActionEvent e) {
        value2Label.setText("0");
    }

    private void threeTrueBtnActionPerformed(ActionEvent e) {
        value3Label.setText("1");
    }

    private void threeFalseBtnActionPerformed(ActionEvent e) {
        value3Label.setText("0");
    }

    private void fourTrueBtnActionPerformed(ActionEvent e) {
        value4Label.setText("1");
    }

    private void fourFalseBtnActionPerformed(ActionEvent e) {
        value4Label.setText("0");
    }

    private void confirmBtnActionPerformed(ActionEvent e) {
        iE.waitForNextBtn = true;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Maximilian Meier
        imagePanel = new JPanel();
        input1Label = new JLabel();
        oneTrueBtn = new JButton();
        oneFalseBtn = new JButton();
        value1Label = new JLabel();
        input2Label = new JLabel();
        twoTrueBtn = new JButton();
        twoFalseBtn = new JButton();
        value2Label = new JLabel();
        input3Label = new JLabel();
        threeTrueBtn = new JButton();
        threeFalseBtn = new JButton();
        value3Label = new JLabel();
        input4Label = new JLabel();
        fourTrueBtn = new JButton();
        fourFalseBtn = new JButton();
        value4Label = new JLabel();
        confirmBtn = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        //======== imagePanel ========
        {
            imagePanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                    .EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder.CENTER, javax
                    .swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD,
                    12), java.awt.Color.red), imagePanel.getBorder()));
            imagePanel.addPropertyChangeListener(new java.beans
                    .PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062ord\u0065r".equals(e.
                            getPropertyName())) throw new RuntimeException();
                }
            });
            imagePanel.setLayout(new FlowLayout());

        }
        contentPane.add(imagePanel);
        imagePanel.setBounds(10, 10, 1400, 400);

        //---- input1Label ----
        input1Label.setText("Input 1: ");
        contentPane.add(input1Label);
        input1Label.setBounds(510, 420, 80, input1Label.getPreferredSize().height);

        //---- oneTrueBtn ----
        oneTrueBtn.setText("1");
        oneTrueBtn.addActionListener(e -> oneTrueBtnActionPerformed(e));
        contentPane.add(oneTrueBtn);
        oneTrueBtn.setBounds(510, 445, 85, oneTrueBtn.getPreferredSize().height);

        //---- oneFalseBtn ----
        oneFalseBtn.setText("0");
        oneFalseBtn.addActionListener(e -> oneFalseBtnActionPerformed(e));
        contentPane.add(oneFalseBtn);
        oneFalseBtn.setBounds(510, 485, 85, oneFalseBtn.getPreferredSize().height);

        //---- value1Label ----
        value1Label.setText("0");
        contentPane.add(value1Label);
        value1Label.setBounds(515, 520, 70, 25);

        //---- input2Label ----
        input2Label.setText("Input 2: ");
        contentPane.add(input2Label);
        input2Label.setBounds(615, 420, 80, 16);

        //---- twoTrueBtn ----
        twoTrueBtn.setText("1");
        twoTrueBtn.addActionListener(e -> twoTrueBtnActionPerformed(e));
        contentPane.add(twoTrueBtn);
        twoTrueBtn.setBounds(615, 445, 78, 30);

        //---- twoFalseBtn ----
        twoFalseBtn.setText("0");
        twoFalseBtn.addActionListener(e -> twoFalseBtnActionPerformed(e));
        contentPane.add(twoFalseBtn);
        twoFalseBtn.setBounds(615, 485, 78, 30);

        //---- value2Label ----
        value2Label.setText("0");
        contentPane.add(value2Label);
        value2Label.setBounds(620, 520, 70, 25);

        //---- input3Label ----
        input3Label.setText("Input 3: ");
        contentPane.add(input3Label);
        input3Label.setBounds(720, 420, 80, 16);

        //---- threeTrueBtn ----
        threeTrueBtn.setText("1");
        threeTrueBtn.addActionListener(e -> threeTrueBtnActionPerformed(e));
        contentPane.add(threeTrueBtn);
        threeTrueBtn.setBounds(720, 445, 78, 30);

        //---- threeFalseBtn ----
        threeFalseBtn.setText("0");
        threeFalseBtn.addActionListener(e -> threeFalseBtnActionPerformed(e));
        contentPane.add(threeFalseBtn);
        threeFalseBtn.setBounds(720, 485, 78, 30);

        //---- value3Label ----
        value3Label.setText("0");
        contentPane.add(value3Label);
        value3Label.setBounds(725, 520, 70, 25);

        //---- input4Label ----
        input4Label.setText("Input 4: ");
        contentPane.add(input4Label);
        input4Label.setBounds(825, 420, 80, 16);

        //---- fourTrueBtn ----
        fourTrueBtn.setText("1");
        fourTrueBtn.addActionListener(e -> fourTrueBtnActionPerformed(e));
        contentPane.add(fourTrueBtn);
        fourTrueBtn.setBounds(825, 445, 78, 30);

        //---- fourFalseBtn ----
        fourFalseBtn.setText("0");
        fourFalseBtn.addActionListener(e -> fourFalseBtnActionPerformed(e));
        contentPane.add(fourFalseBtn);
        fourFalseBtn.setBounds(825, 485, 78, 30);

        //---- value4Label ----
        value4Label.setText("0");
        contentPane.add(value4Label);
        value4Label.setBounds(830, 520, 70, 25);

        //---- confirmBtn ----
        confirmBtn.setText("Next");
        confirmBtn.addActionListener(e -> confirmBtnActionPerformed(e));
        contentPane.add(confirmBtn);
        confirmBtn.setBounds(945, 415, confirmBtn.getPreferredSize().width, 130);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Maximilian Meier
    public JPanel imagePanel;
    public JLabel input1Label;
    public JButton oneTrueBtn;
    public JButton oneFalseBtn;
    public JLabel value1Label;
    public JLabel input2Label;
    public JButton twoTrueBtn;
    public JButton twoFalseBtn;
    public JLabel value2Label;
    public JLabel input3Label;
    public JButton threeTrueBtn;
    public JButton threeFalseBtn;
    public JLabel value3Label;
    public JLabel input4Label;
    public JButton fourTrueBtn;
    public JButton fourFalseBtn;
    public JLabel value4Label;
    public JButton confirmBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
