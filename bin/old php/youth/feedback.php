<?php
// youth_feedback.php

require_once('youth_sidebar.php');

// PHP Data Stubs
$recent_feedback = [
    ['title' => 'The event was fantastic!', 'status' => 'Resolved', 'type' => 'Appreciation', 'date' => 'Feb 10, 2026', 'status_class' => 'text-green-700', 'border_class' => 'border-green-500', 'bg_class' => 'bg-green-50'],
    ['title' => 'Website broken on mobile', 'status' => 'Pending Review', 'type' => 'Technical Issue', 'date' => 'Feb 05, 2026', 'status_class' => 'text-red-700', 'border_class' => 'border-red-500', 'bg_class' => 'bg-red-50'],
    ['title' => 'Suggestion: Add a new type of seminar', 'status' => 'Pending Review', 'type' => 'General Inquiry', 'date' => 'Jan 25, 2026', 'status_class' => 'text-yellow-700', 'border_class' => 'border-yellow-500', 'bg_class' => 'bg-yellow-50'],
];

$message = '';
$form_data = ['feedback_type' => '', 'linked_item' => '', 'details' => ''];

// Handle Form Submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // In a real application, you would perform validation and a MySQL INSERT query here.
    $form_data['feedback_type'] = trim($_POST['feedback_type'] ?? '');
    $form_data['linked_item'] = trim($_POST['linked_item'] ?? '');
    $form_data['details'] = trim($_POST['details'] ?? '');
    
    if (empty($form_data['feedback_type']) || empty($form_data['details'])) {
         $message = '<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Error!</strong><span class="block sm:inline"> Please fill out the Type and Details fields.</span></div>';
    } else {
         $message = '<div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Success!</strong><span class="block sm:inline"> Your feedback has been submitted!</span></div>';
         // Clear form data on success (prevents resubmission and clears textareas)
         $form_data = ['feedback_type' => '', 'linked_item' => '', 'details' => ''];
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Feedback | SK Youth Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="loader.js" defer></script>
</head>
<body class="bg-gray-50">
    <aside>
        <?php render_youth_sidebar('feedback'); ?>
    </aside>
    
    <main class="ml-64 flex-1 p-8 overflow-y-auto">
        <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">Feedback & Support</h2>
        <p class="text-gray-600 mb-8">Submit suggestions, concerns, or report issues directly to SK Officials. Your voice matters!</p>

        <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <div class="lg:col-span-2 bg-white p-6 rounded-xl shadow-2xl">
                <h3 class="text-xl font-semibold mb-4"><i class="fas fa-comment-dots mr-2 text-indigo-600"></i> Submit New Feedback</h3>
                
                <?php echo $message; ?>

                <form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                    
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                            <label for="feedback_type" class="block text-sm font-medium text-gray-700">Type of Feedback</label>
                            <select id="feedback_type" name="feedback_type" required 
                                    onchange="toggleLinkedItemField(this.value)"
                                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                <option value="" disabled <?php echo empty($form_data['feedback_type']) ? 'selected' : ''; ?>>Select an option</option>
                                <option value="general" <?php echo ($form_data['feedback_type'] === 'general' ? 'selected' : ''); ?>>General Suggestion/Inquiry</option>
                                <option value="event" <?php echo ($form_data['feedback_type'] === 'event' ? 'selected' : ''); ?>>Feedback on a specific Event</option>
                                <option value="opportunity" <?php echo ($form_data['feedback_type'] === 'opportunity' ? 'selected' : ''); ?>>Feedback on an Opportunity</option>
                                <option value="technical" <?php echo ($form_data['feedback_type'] === 'technical' ? 'selected' : ''); ?>>Technical Issue/Bug Report</option>
                            </select>
                        </div>
                        
                        <div id="linked_item_field" class="hidden">
                            <label for="linked_item" class="block text-sm font-medium text-gray-700">Event/Opportunity Name or ID <span class="text-red-500">*</span></label>
                            <input type="text" id="linked_item" name="linked_item" 
                                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500"
                                placeholder="e.g., Coastal Clean-Up Drive"
                                value="<?php echo htmlspecialchars($form_data['linked_item']); ?>">
                        </div>
                    </div>
                    
                    <div class="mb-6">
                        <label for="details" class="block text-sm font-medium text-gray-700">Details of your Feedback <span class="text-red-500">*</span></label>
                        <textarea id="details" name="details" rows="6" required 
                                  class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500"
                                  placeholder="Describe your suggestion, concern, or report..."><?php echo htmlspecialchars($form_data['details']); ?></textarea>
                    </div>
                    
                    <button type="submit" class="w-full py-3 bg-indigo-600 text-white font-medium rounded-lg hover:bg-indigo-700 transition shadow-md">
                        <i class="fas fa-paper-plane mr-2"></i> Send Feedback
                    </button>
                </form>
            </div>

            <div class="lg:col-span-1">
                <div class="bg-white p-6 rounded-xl shadow-xl">
                    <h3 class="text-xl font-semibold text-gray-900 mb-4 border-b pb-2"><i class="fas fa-history mr-2 text-gray-500"></i> My Recent Feedback</h3>
                    <ul class="space-y-4">
                        <?php foreach ($recent_feedback as $feedback): ?>
                            <li class="border-l-4 <?php echo htmlspecialchars($feedback['border_class']); ?> p-3 <?php echo htmlspecialchars($feedback['bg_class']); ?> rounded-lg">
                                <p class="font-medium text-gray-900 line-clamp-1"><?php echo htmlspecialchars($feedback['title']); ?></p>
                                <span class="text-xs <?php echo htmlspecialchars($feedback['status_class']); ?> font-semibold">
                                    <i class="fas fa-<?php echo ($feedback['status'] === 'Resolved' ? 'check-circle' : 'hourglass-half'); ?> mr-1"></i> <?php echo htmlspecialchars($feedback['status']); ?>
                                </span>
                                <p class="text-xs text-gray-500"><?php echo htmlspecialchars($feedback['type']); ?> | <?php echo htmlspecialchars($feedback['date']); ?></p>
                            </li>
                        <?php endforeach; ?>
                    </ul>
                </div>
            </div>
        </div>
    </main>
    
    <script>
        function toggleLinkedItemField(value) {
            const fieldDiv = document.getElementById('linked_item_field');
            const linkedItemInput = document.getElementById('linked_item');

            if (value === 'event' || value === 'opportunity') {
                fieldDiv.classList.remove('hidden');
                linkedItemInput.setAttribute('required', 'required');
            } else {
                fieldDiv.classList.add('hidden');
                linkedItemInput.removeAttribute('required');
                linkedItemInput.value = ''; 
            }
        }
        
        document.addEventListener('DOMContentLoaded', () => {
             const currentType = document.getElementById('feedback_type').value;
             toggleLinkedItemField(currentType);
        });
    </script>
</body>
</html>