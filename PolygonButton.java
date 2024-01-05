import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {

            if (SwingUtilities.isLeftMouseButton(event)) { // Check if the click is a left click
                if (++pointCount == 1) {
                    polygonCommand = new PolygonCommand(View.mapPoint(event.getPoint()));
                    undoManager.beginCommand(polygonCommand);
                    pointCount++;
                } else if (pointCount > 0) {
                    polygonCommand.setPolyPoint(View.mapPoint(event.getPoint()));
                    polygonCommand.execute();

                }

            } else if (SwingUtilities.isRightMouseButton(event)) {
                if (pointCount > 3) {
                    pointCount = 0;
                    drawingPanel.removeMouseListener(this);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    undoManager.endCommand(polygonCommand);
                }

            }

        }
    }
}