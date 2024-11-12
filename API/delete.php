<?php
include 'db.php';

header('Content-Type: application/json'); // Set the content type to JSON

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];

    $sql = "DELETE FROM student_details WHERE id = ?";
    $stmt = $pdo->prepare($sql);

    if ($stmt->execute([$id])) {
        echo json_encode(["message" => "Record deleted successfully"]);
    } else {
        echo json_encode(["error" => "Failed to delete record"]);
    }
}
?>
