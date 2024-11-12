<?php
include 'db.php';

header('Content-Type: application/json'); // Set the content type to JSON

$sql = "SELECT * FROM student_details";
$stmt = $pdo->prepare($sql);
$stmt->execute();
$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

echo json_encode($result); // Encode the result as JSON
?>
