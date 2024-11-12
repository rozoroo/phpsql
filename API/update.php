<?php
include 'db.php';

header('Content-Type: application/json'); // Set the content type to JSON

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $name = $_POST['name'];
    $class = $_POST['class'];

    $sql = "UPDATE student_details SET name = ?, class = ? WHERE id = ?";
    $stmt = $pdo->prepare($sql);

    if ($stmt->execute([$name, $class, $id])) {
        echo json_encode(["message" => "Record updated successfully"]);
    } else {
        echo json_encode(["error" => "Failed to update record"]);
    }
}
?>
