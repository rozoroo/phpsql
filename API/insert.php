<?php
include 'db.php';

header('Content-Type: application/json'); // Set the content type to JSON

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $class = $_POST['class'];

    $sql = "INSERT INTO student_details (name, class) VALUES (?, ?)";
    $stmt = $pdo->prepare($sql);

    if ($stmt->execute([$name, $class])) {
        echo json_encode(["message" => "Program added successfully"]);
    } else {
        echo json_encode(["error" => "Failed to add program"]);
    }
}
?>
