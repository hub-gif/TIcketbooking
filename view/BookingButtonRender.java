package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class BookingButtonRender extends JButton implements TableCellRenderer {
    public BookingButtonRender() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("订票");
        return this;
    }
}