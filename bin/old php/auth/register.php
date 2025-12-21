<?php
// --- PHP Backend Logic for Registration ---

$error_message = '';
$success_message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // 1. Get and sanitize form data
    $fullName = trim($_POST['fullName'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $password = $_POST['password'] ?? '';
    $confirmPassword = $_POST['confirmPassword'] ?? '';
    $role = $_POST['role'] ?? 'youth'; // Default role is youth

    // 2. Simple server-side validation (Password Match)
    if ($password !== $confirmPassword) {
        $error_message = "Error: Passwords do not match.";
    } elseif (empty($fullName) || empty($email) || empty($password)) {
        $error_message = "Error: All fields are required.";
    } else {
        // --- THIS IS WHERE YOUR MYSQL/DATABASE LOGIC GOES ---

        // *******************************************************************
        // 3. Connect to MySQL (Using MySQLi or PDO)
        // Example (You must replace these placeholders with your actual credentials):
        // $conn = new mysqli('localhost', 'db_user', 'db_password', 'db_name');
        // if ($conn->connect_error) {
        //     die("Connection failed: " . $conn->connect_error);
        // }

        // 4. Hash the password before storing it!
        $hashed_password = password_hash($password, PASSWORD_DEFAULT);

        // 5. Prepare and execute the INSERT query
        // $stmt = $conn->prepare("INSERT INTO users (full_name, email, password, role) VALUES (?, ?, ?, ?)");
        // $stmt->bind_param("ssss", $fullName, $email, $hashed_password, $role);
        
        // if ($stmt->execute()) {
        //     $success_message = "Registration successful! You can now log in.";
        //     header("Location: login.php?registered=success");
        //     exit();
        // } else {
        //     $error_message = "Database Error: Registration failed. (" . $conn->error . ")";
        // }

        // $stmt->close();
        // $conn->close();
        // *******************************************************************
        
        // --- Temporary Success Message (for demonstration before DB connection) ---
        $success_message = "Registration data received! (Database connection pending)";
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SK System Registration</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .body-bg {
            background-color: #f9fafb;
        }
    </style>
</head>
<body class="body-bg min-h-screen flex items-center justify-center p-4 antialiased">

    <div class="w-full max-w-lg bg-white rounded-2xl shadow-xl border border-gray-100 overflow-hidden">
        <div class="p-8 sm:p-10">
            <header class="text-center mb-8">
                <div class="text-3xl mb-1 font-extrabold text-gray-900 tracking-tight">Create Your SK Connect Account</div>
                <p class="text-gray-500 text-base">Register as a Youth User to get started.</p>
            </header>

            <?php if (!empty($error_message)): ?>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <strong class="font-bold">Error!</strong>
                    <span class="block sm:inline"><?php echo $error_message; ?></span>
                </div>
            <?php endif; ?>
            <?php if (!empty($success_message)): ?>
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <strong class="font-bold">Success!</strong>
                    <span class="block sm:inline"><?php echo $success_message; ?></span>
                </div>
            <?php endif; ?>
            <form id="registerForm" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                
                <div class="mb-5">
                    <label for="fullName" class="block text-sm font-medium text-gray-700">Full Name</label>
                    <input type="text" id="fullName" name="fullName" required 
                        class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                        placeholder="Juan Dela Cruz" value="<?php echo htmlspecialchars($fullName ?? ''); ?>">
                </div>

                <div class="mb-5">
                    <label for="email" class="block text-sm font-medium text-gray-700">Email Address</label>
                    <input type="email" id="email" name="email" required 
                        class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                        placeholder="you@example.com" value="<?php echo htmlspecialchars($email ?? ''); ?>">
                </div>

                <div class="grid grid-cols-2 gap-4 mb-6">
                    <div>
                        <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                        <input type="password" id="password" name="password" required 
                            class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                            placeholder="••••••••">
                    </div>
                    
                    <div>
                        <label for="confirmPassword" class="block text-sm font-medium text-gray-700">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required 
                            class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                            placeholder="••••••••">
                    </div>
                </div>

                <input type="hidden" name="role" value="youth">

                <button type="submit" 
                        class="mt-4 w-full flex justify-center items-center py-2.5 px-4 border border-transparent rounded-lg shadow-md text-base font-semibold text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 transition duration-300">
                    Register Account
                    <svg class="ml-2 w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path></svg>
                </button>
            </form>

            <p class="mt-7 text-center text-sm text-gray-500">
                Already have an account? 
                <a href="login.php" class="font-semibold text-blue-600 hover:text-blue-700 transition duration-150">Log In Here</a>
            </p>
        </div>
    </div>

    </body>
</html>