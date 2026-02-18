

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Font;

public class EventBookingSystem extends JFrame implements ActionListener {
    // GUI Components
    private JTextField nameField, guestsField, dateField, timeField;
    private JComboBox<String> eventTypeCombo;
    private JCheckBox photographerCheckBox;
    private JButton bookButton;

    public EventBookingSystem() {
        // Set up the frame
        setTitle("Event Booking System");
        setSize(603, 394);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Initialize GUI components
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameLabel.setBounds(21, 13, 66, 21);
        JLabel eventTypeLabel = new JLabel("Event Type:");
        eventTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventTypeLabel.setBounds(21, 44, 95, 17);
        JLabel guestsLabel = new JLabel("Number of Guests:");
        guestsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        guestsLabel.setBounds(21, 67, 148, 13);
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateLabel.setBounds(21, 102, 148, 13);
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timeLabel.setBounds(31, 131, 56, 13);
        JLabel photographerLabel = new JLabel("Photographer:");
        photographerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        photographerLabel.setBounds(21, 160, 102, 21);
        nameField = new JTextField(20);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nameField.setBounds(185, 14, 189, 21);
        eventTypeCombo = new JComboBox<>(new String[]{"Wedding", "Birthday", "Corporate", "Other"});
        eventTypeCombo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        eventTypeCombo.setBounds(185, 42, 189, 19);
        guestsField = new JTextField(5);
        guestsField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        guestsField.setBounds(185, 67, 189, 21);
        dateField = new JTextField(10);
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dateField.setBounds(185, 98, 189, 21);
        timeField = new JTextField(10);
        timeField.setBounds(185, 129, 189, 21);
        photographerCheckBox = new JCheckBox();
        photographerCheckBox.setBounds(129, 160, 21, 21);
        bookButton = new JButton("Book Event");
        bookButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bookButton.setBounds(110, 191, 128, 21);
        bookButton.addActionListener(this);
        // Layout components
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(eventTypeLabel);
        panel.add(eventTypeCombo);
        panel.add(guestsLabel);
        panel.add(guestsField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(photographerLabel);
        panel.add(photographerCheckBox);
        panel.add(bookButton);

// Add panel to frame
        getContentPane().add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            // Get user inputs
            String name = nameField.getText();
            String eventType = (String) eventTypeCombo.getSelectedItem();
            int guests = Integer.parseInt(guestsField.getText());
            String date = dateField.getText();
            String time = timeField.getText();
            boolean photographerNeeded = photographerCheckBox.isSelected();
            // Calculate estimate (you can add more complex logic here)
            double estimate = calculateEstimate(eventType, guests, photographerNeeded);
// Display estimate to user
            JOptionPane.showMessageDialog(this, "Dear " + name + ",\n" +
                    "Your event booking for " + eventType + " with " + guests + " guests on " +
                    date + " at " + time + " has been estimated at $" + estimate);
            // Save data to file
            saveBookingToFile(name, eventType, guests, date, time, photographerNeeded, estimate);
        }
    }
    private double calculateEstimate(String eventType, int guests, boolean photographerNeeded) {
        // Add your logic to calculate the estimate based on event type, number of guests, and photographer requirement
        // This is just a placeholder
        double basePrice = 1000; // Base price for any event
        double perGuestPrice = 20; // Additional price per guest
        double photographerPrice = photographerNeeded ? 500 : 0; // Additional price if photographer is needed
        return basePrice + (perGuestPrice * guests) + photographerPrice;
    }
    private void saveBookingToFile(String name, String eventType, int guests, String date, String time,
                                   boolean photographerNeeded, double estimate) {
        // Write data to a file
        try (PrintWriter writer = new PrintWriter(new FileWriter("bookings.txt", true))) {
            writer.println(name + "," + eventType + "," + guests + "," + date + "," + time + "," +
                    photographerNeeded + "," + estimate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventBookingSystem bookingSystem = new EventBookingSystem();
        bookingSystem.setVisible(true);
    }
}