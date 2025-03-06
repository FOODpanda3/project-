package javaapplication22;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransactionFrame1 extends javax.swing.JFrame {
    
    private List<Object[]> selectedProducts; // Store selected products

    public TransactionFrame1(List<Object[]> products) {
        initComponents();
        this.selectedProducts = (products != null) ? products : new java.util.ArrayList<>();

        // Populate only the ComboBox, NOT the Table
        updateComboBox();
        this.setVisible(true);
    }

    // Populate the ComboBox with products passed from MainFrame
    private void updateComboBox() {
        selectproduct.removeAllItems(); // Clear previous items

        for (Object[] product : selectedProducts) {
            if (product.length >= 2) {
                String id = product[0].toString();
                String productName = product[1].toString();
                selectproduct.addItem(id + " - " + productName);
            }
        }
    }

    // When the "Add to Cart" button is clicked
    private void addtoCart1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedItem = (String) selectproduct.getSelectedItem();
        String quantityText = Quantity.getText();

        // Validate product selection
        if (selectedItem == null || selectedItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product.");
            return;
        }

        // Validate quantity input
        if (quantityText.isEmpty() || !quantityText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }

        int quantity = Integer.parseInt(quantityText);

        // Extract product ID and Name
        String[] parts = selectedItem.split(" - ");
        if (parts.length < 2) {
            JOptionPane.showMessageDialog(this, "Invalid product format.");
            return;
        }

        String productId = parts[0];
        String productName = parts[1];

        // Get product price from the list
        double price = getProductPriceById(productId);
        double total = price * quantity;

        // Add the product to the table
        addProductToTable(productId, productName, price, quantity, total);
    }

    // Function to find product price from the selected list
    private double getProductPriceById(String productId) {
        for (Object[] product : selectedProducts) {
            if (product[0].toString().equals(productId)) {
                return (Double) product[2]; // Assuming price is stored in index 2
            }
        }
        return 0.0; // Default if not found
    }

    // Function to add products to the table manually
    private void addProductToTable(String id, String product, Double price, int quantity, double total) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        // Check if product already exists in the table
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(id)) {
                // Update quantity and total price
                int existingQuantity = Integer.parseInt(model.getValueAt(i, 3).toString());
                int newQuantity = existingQuantity + quantity;
                model.setValueAt(newQuantity, i, 3);
                model.setValueAt(price * newQuantity, i, 4);
                return;
            }
        }

        // If not found, add new row
        model.addRow(new Object[]{id, product, price, quantity, total});
    }

    // "Back" button to return to MainFrame without affecting data
    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new MainFrame(selectedProducts).setVisible(true);
    }
}
