import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private UndoManager undoManager;
    private MoveCommand moveCommand;
    private MouseHandler mouseHandler;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler(); // Instantiate the MouseHandler
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private Point startPoint;

        @Override
        public void mousePressed(MouseEvent event) {
            startPoint = View.mapPoint(event.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            moveCommand = new MoveCommand(startPoint, View.mapPoint(event.getPoint()));
            undoManager.beginCommand(moveCommand);
            undoManager.endCommand(moveCommand);
            drawingPanel.removeMouseListener(this);
            drawingPanel.removeMouseMotionListener(this);
            view.setCursor(Cursor.getDefaultCursor());
        }
    }

}
