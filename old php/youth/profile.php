<?php
// youth_profile.php

require_once('youth_sidebar.php');

// PHP Data Stubs (Replace with database fetch logic)
$user_data = [
    'first_name' => 'Juan',
    'middle_name' => 'Santos',
    'surname' => 'Dela Cruz',
    'email' => 'juan.dela.cruz@example.com',
    'phone' => '09171234567',
    'birthdate' => '2005-06-15', // Read Only
    'gender' => 'Male',
    'address' => '123 Sampaguita St.',
    'barangay' => 'Purok 5',
    'youth_status' => 'Registered', // Read Only
    'voter_status' => 'Registered (Active)', // Read Only
    'voter_precinct' => '1234-A' // Read Only
];

$message = '';

// Handle Form Submission
if ($_SERVER["REQUEST_METHOD"] == "POST" && ($_POST['save_changes'] ?? '0') === '1') {
    // In a real application, you would perform validation and a MySQL UPDATE query here.
    $user_data['first_name'] = $_POST['first_name'] ?? $user_data['first_name'];
    $user_data['middle_name'] = $_POST['middle_name'] ?? $user_data['middle_name'];
    $user_data['surname'] = $_POST['surname'] ?? $user_data['surname'];
    $user_data['phone'] = $_POST['phone'] ?? $user_data['phone'];
    $user_data['gender'] = $_POST['gender'] ?? $user_data['gender'];
    $user_data['address'] = $_POST['address'] ?? $user_data['address'];
    $user_data['barangay'] = $_POST['barangay'] ?? $user_data['barangay'];
    
    // Simulate successful update
    $message = '<div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Success!</strong><span class="block sm:inline"> Your profile has been updated.</span></div>';
    
    // Optional: Re-render the page in disabled mode after saving
    // header("Location: youth_profile.php"); 
    // exit();
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile | SK Youth Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="loader.js" defer></script>
</head>
<body class="bg-gray-50">
    <aside>
        <?php render_youth_sidebar('profile'); ?>
    </aside>
    
    <main class="ml-64 flex-1 p-8 overflow-y-auto">
        <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">My Profile and Personal Information</h2>
        <p class="text-gray-600 mb-8">Review and update your personal details. <span class="text-red-600 font-semibold">Fields marked with * are required for SK registration.</span></p>

        <div class="max-w-5xl mx-auto bg-white p-8 rounded-xl shadow-2xl">
            <?php echo $message; ?>
            
            <div class="flex justify-between items-center mb-6 border-b pb-4">
                <h3 class="text-xl font-semibold text-gray-900"><i class="fas fa-id-card-alt mr-2 text-indigo-600"></i> Personal Details</h3>
                <button type="button" id="editButton" onclick="toggleEditMode()" 
                        class="py-2 px-4 bg-indigo-600 text-white font-medium rounded-lg hover:bg-indigo-700 transition duration-150">
                    <i class="fas fa-edit mr-1"></i> Edit Profile
                </button>
            </div>
            
            <form id="profileForm" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                
                <input type="hidden" name="save_changes" id="save_changes_input" value="0">

                <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                    
                    <div>
                        <label for="first_name" class="block text-sm font-medium text-gray-700">First Name <span class="text-red-500">*</span></label>
                        <input type="text" id="first_name" name="first_name" required value="<?php echo htmlspecialchars($user_data['first_name']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                    </div>
                    <div>
                        <label for="middle_name" class="block text-sm font-medium text-gray-700">Middle Name</label>
                        <input type="text" id="middle_name" name="middle_name" value="<?php echo htmlspecialchars($user_data['middle_name']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50">
                    </div>
                    <div>
                        <label for="surname" class="block text-sm font-medium text-gray-700">Surname <span class="text-red-500">*</span></label>
                        <input type="text" id="surname" name="surname" required value="<?php echo htmlspecialchars($user_data['surname']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                    </div>
                    
                    <div class="md:col-span-1">
                        <label for="email" class="block text-sm font-medium text-gray-700">Email Address (Read Only)</label>
                        <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($user_data['email']); ?>" disabled readonly
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-200 text-gray-600 cursor-not-allowed">
                    </div>
                    
                    <div class="md:col-span-1">
                        <label for="phone" class="block text-sm font-medium text-gray-700">Contact Number <span class="text-red-500">*</span></label>
                        <input type="tel" id="phone" name="phone" required value="<?php echo htmlspecialchars($user_data['phone']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                    </div>

                    <div class="md:col-span-1">
                        <label for="birthdate" class="block text-sm font-medium text-gray-700">Birthdate (Read Only)</label>
                        <input type="date" id="birthdate" name="birthdate" value="<?php echo htmlspecialchars($user_data['birthdate']); ?>" disabled readonly
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-200 text-gray-600 cursor-not-allowed">
                    </div>

                    <div>
                        <label for="gender" class="block text-sm font-medium text-gray-700">Gender <span class="text-red-500">*</span></label>
                        <select id="gender" name="gender" required disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                            <option value="">Select Gender</option>
                            <option value="Male" <?php echo ($user_data['gender'] === 'Male' ? 'selected' : ''); ?>>Male</option>
                            <option value="Female" <?php echo ($user_data['gender'] === 'Female' ? 'selected' : ''); ?>>Female</option>
                            <option value="Other" <?php echo ($user_data['gender'] === 'Other' ? 'selected' : ''); ?>>Prefer not to say</option>
                        </select>
                    </div>

                    <div>
                        <label for="barangay" class="block text-sm font-medium text-gray-700">Barangay <span class="text-red-500">*</span></label>
                        <input type="text" id="barangay" name="barangay" required value="<?php echo htmlspecialchars($user_data['barangay']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                    </div>

                    <div class="md:col-span-3">
                        <label for="address" class="block text-sm font-medium text-gray-700">Complete Address <span class="text-red-500">*</span></label>
                        <input type="text" id="address" name="address" required value="<?php echo htmlspecialchars($user_data['address']); ?>" disabled
                            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg bg-gray-50 focus:ring-indigo-500 focus:border-indigo-500">
                    </div>

                </div>

                <div class="mt-8">
                    <h3 class="text-xl font-semibold text-gray-900 mb-4 border-b pb-2"><i class="fas fa-check-double mr-2 text-green-600"></i> Youth & Voter Status</h3>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div class="bg-gray-50 p-4 rounded-lg border border-gray-200">
                            <p class="text-sm font-medium text-gray-700">SK Youth Registry Status</p>
                            <p class="text-xl font-bold text-indigo-600 mt-1"><?php echo htmlspecialchars($user_data['youth_status']); ?></p>
                        </div>
                        <div class="bg-gray-50 p-4 rounded-lg border border-gray-200">
                            <p class="text-sm font-medium text-gray-700">Voter Status</p>
                            <p class="text-xl font-bold text-green-600 mt-1"><?php echo htmlspecialchars($user_data['voter_status']); ?></p>
                            <p class="text-xs text-gray-500 mt-1">Precinct: <?php echo htmlspecialchars($user_data['voter_precinct']); ?></p>
                        </div>
                    </div>
                </div>

                <button type="submit" id="submitFormButton" class="hidden"></button>
            </form>
        </div>

        <script>
            function toggleEditMode() {
                const form = document.getElementById('profileForm');
                const editButton = document.getElementById('editButton');
                const submitButton = document.getElementById('submitFormButton');
                const saveChangesInput = document.getElementById('save_changes_input');
                
                // If currently NOT in edit mode, switch to edit mode
                if (!form.classList.contains('edit-mode')) {
                    form.classList.add('edit-mode');
                    const inputs = form.querySelectorAll('input:not([id="email"]):not([id="birthdate"]):not([id="voter_status"]):not([id="youth_status"]), select');

                    inputs.forEach(input => {
                        input.disabled = false;
                        input.classList.remove('bg-gray-50');
                        input.classList.add('bg-white');
                    });

                    // Update button text and color for Edit -> Save
                    editButton.innerHTML = '<i class="fas fa-save mr-1"></i> Save Changes';
                    editButton.classList.remove('bg-indigo-600', 'hover:bg-indigo-700');
                    editButton.classList.add('bg-green-600', 'hover:bg-green-700');
                    saveChangesInput.value = '1'; // Signal that a save is imminent
                } else {
                    // If currently in edit mode, trigger form submit
                    submitButton.click();
                }
            }
        </script>
    </main>
</body>
</html>