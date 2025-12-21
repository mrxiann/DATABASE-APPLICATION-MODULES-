<?php
session_start();

// --- PHP Backend Logic for Login ---
$error_message = '';
$email = '';

// Check if registration was successful (redirected from register.php)
if (isset($_GET['registered']) && $_GET['registered'] === 'success') {
    $success_message = "Registration successful! Please log in with your new account.";
} else {
    $success_message = '';
}

// Check if user is already logged in
if (isset($_SESSION['user_id'])) {
    if ($_SESSION['role'] === 'admin') {
        header("Location: admin_dashboard.php");
    } else {
        header("Location: youth_dashboard.php");
    }
    exit();
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // 1. Get and sanitize form data
    $email = trim($_POST['email'] ?? '');
    $password = $_POST['password'] ?? '';
    $role = $_POST['role'] ?? 'youth'; 

    // 2. Simple validation
    if (empty($email) || empty($password)) {
        $error_message = "Error: Please enter both email and password.";
    } else {
        // --- TEMPORARY DEMO LOGIC - REPLACE WITH DATABASE ---
        
        // For demo purposes only - hardcoded credentials
        $demo_users = [
            'admin@sk.com' => [
                'password' => 'admin123',
                'role' => 'admin',
                'name' => 'Admin Officer',
                'id' => 1
            ],
            'youth@sk.com' => [
                'password' => 'youth123',
                'role' => 'youth',
                'name' => 'Juan Dela Cruz',
                'id' => 2
            ]
        ];
        
        if (isset($demo_users[$email]) && $demo_users[$email]['password'] === $password) {
            // Password matches (in real app, use password_verify())
            $_SESSION['user_id'] = $demo_users[$email]['id'];
            $_SESSION['role'] = $demo_users[$email]['role'];
            $_SESSION['user_name'] = $demo_users[$email]['name'];
            $_SESSION['email'] = $email;
            
            // Redirect based on role
            if ($demo_users[$email]['role'] === 'admin') {
                header("Location: admin_dashboard.php");
            } else {
                header("Location: youth_dashboard.php");
            }
            exit();
        } else {
            $error_message = "Invalid email or password.";
        }
        // --- END TEMPORARY DEMO LOGIC ---
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SK System Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .body-bg {
            background-color: #f9fafb;
        }
        input[type="radio"]:checked + .role-card {
            border-color: #2563eb; 
            background-color: #eff6ff;
            box-shadow: 0 4px 10px rgba(37, 99, 235, 0.1);
        }
    </style>
</head>
<body class="body-bg min-h-screen flex items-center justify-center p-4 antialiased">

    <div class="w-full max-w-md bg-white rounded-2xl shadow-xl border border-gray-100 overflow-hidden">
        <div class="p-8 sm:p-10">
            <header class="text-center mb-10">
                <div class="text-4xl mb-2 font-extrabold text-gray-900 tracking-tight">SK Connect</div>
                <p class="text-gray-500 text-base">Sign in to your account.</p>
            </header>

            <?php if (!empty($error_message)): ?>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <strong class="font-bold">Login Failed!</strong>
                    <span class="block sm:inline"><?php echo $error_message; ?></span>
                </div>
            <?php endif; ?>
            <?php if (!empty($success_message)): ?>
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <strong class="font-bold">Success!</strong>
                    <span class="block sm:inline"><?php echo $success_message; ?></span>
                </div>
            <?php endif; ?>
            <form id="loginForm" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                
                <div class="mb-8">
                    <label class="block text-sm font-semibold text-gray-700 mb-3">Select Your Role</label>
                    <div class="flex space-x-4">
                        
                        <label class="flex-1">
                            <input type="radio" name="role" value="youth" required class="hidden" <?php if(!isset($_POST['role']) || (isset($_POST['role']) && $_POST['role'] === 'youth')) echo 'checked'; ?>>
                            <div class="role-card p-4 rounded-xl border border-gray-200 cursor-pointer transition duration-300 ease-in-out hover:border-blue-400 flex flex-col items-center justify-center space-y-1">
                                <svg class="w-6 h-6 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path></svg>
                                <span class="text-sm font-medium text-gray-800">Youth User</span>
                            </div>
                        </label>
                        
                        <label class="flex-1">
                            <input type="radio" name="role" value="admin" required class="hidden" <?php if(isset($_POST['role']) && $_POST['role'] === 'admin') echo 'checked'; ?>>
                            <div class="role-card p-4 rounded-xl border border-gray-200 cursor-pointer transition duration-300 ease-in-out hover:border-blue-400 flex flex-col items-center justify-center space-y-1">
                                <svg class="w-6 h-6 text-indigo-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.047 12.008 12.008 0 00-2.31 3.393 12.004 12.004 0 001.295 14.881A12.001 12.001 0 0012 22a12.002 12.002 0 009.623-4.706 12.003 12.003 0 001.295-14.881z"></path></svg>
                                <span class="text-sm font-medium text-gray-800">SK Official</span>
                            </div>
                        </label>
                    </div>
                </div>

                <div class="space-y-5">
                    <div>
                        <label for="email" class="block text-sm font-medium text-gray-700">Email Address</label>
                        <input type="email" id="email" name="email" required 
                            class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                            placeholder="you@example.com" value="<?php echo htmlspecialchars($email); ?>">
                    </div>

                    <div>
                        <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                        <input type="password" id="password" name="password" required 
                            class="mt-1 block w-full px-4 py-2.5 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 transition duration-150" 
                            placeholder="••••••••">
                    </div>
                </div>

                <button type="submit" 
                        class="mt-7 w-full flex justify-center items-center py-2.5 px-4 border border-transparent rounded-lg shadow-md text-base font-semibold text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-300">
                    Log In
                    <svg class="ml-2 w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg>
                </button>
            </form>

            <p class="mt-7 text-center text-sm text-gray-500">
                New Youth User? 
                <a href="register.php" class="font-semibold text-blue-600 hover:text-blue-700 transition duration-150">Create an Account</a>
            </p>
        </div>
    </div>

    </body>
</html>