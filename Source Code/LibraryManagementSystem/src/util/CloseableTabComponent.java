/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author HA
 */
public class CloseableTabComponent extends JPanel {
    
    
        private ImageIcon closerImage = new ImageIcon(getClass().getResource("/images/closer.gif"));
        private ImageIcon closerRolloverImage = new ImageIcon(getClass().getResource("/images/closer_rollover.gif"));
        private ImageIcon closerPressedImage = new ImageIcon(getClass().getResource("/images/closer_pressed.gif"));
        private JLabel titleLabel = null;
        private JButton closeButton = null;
        private JTabbedPane tabbedPane = null;
        public CloseableTabComponent(JTabbedPane aTabbedPane, String title) {
            super(new BorderLayout());
            tabbedPane = aTabbedPane;

            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
            titleLabel = new JLabel(title + " ");
            titleLabel.setOpaque(false);

            closeButton = new JButton(closerImage);
            closeButton.setRolloverIcon(closerRolloverImage);
            closeButton.setPressedIcon(closerPressedImage);
            closeButton.setBorderPainted(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setFocusPainted(false);
            closeButton.setRolloverEnabled(true);
            closeButton.setOpaque(false);
            closeButton.setContentAreaFilled(false);
            closeButton.setPreferredSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.setSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        if (CloseableTabComponent.this.equals(tabbedPane.getTabComponentAt(i))) {
                            tabbedPane.removeTabAt(i);
                            break;
                        }
                    }
                }
            });

            add(titleLabel, BorderLayout.CENTER);
            add(closeButton, BorderLayout.EAST);
        }

        
        @Override
        public void paint(Graphics g) {
            super.paint(g);
        }

}
