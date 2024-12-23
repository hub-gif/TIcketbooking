
import javax.swing.*;
import java.awt.*;

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private int row;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        label = "订票";
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            String flightNumber = (String) table.getValueAt(row, 0);
            String name = JOptionPane.showInputDialog(table, "乘客姓名:");
            String idNumber = JOptionPane.showInputDialog(table, "身份证号:");
            String phone = JOptionPane.showInputDialog(table, "联系电话:");
            ((MainFrame) SwingUtilities.getWindowAncestor(table)).searchFlights("", "");
        }
        isPushed = false;
        return label;
    }
}
