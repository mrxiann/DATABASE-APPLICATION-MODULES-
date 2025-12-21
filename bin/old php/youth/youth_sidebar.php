<?php
// youth_sidebar.php

/**
 * Renders the youth sidebar component.
 * @param string $current_page_data_page The data-page attribute value of the current page (e.g., 'dashboard', 'events').
 */
function render_youth_sidebar($current_page_data_page) {
    // Map data-page attributes to file names and icon/text
    $nav_items = [
        'dashboard' => ['href' => 'youth_dashboard.php', 'icon' => 'fas fa-home', 'text' => 'Dashboard'],
        'events' => ['href' => 'youth_events.php', 'icon' => 'fas fa-calendar-alt', 'text' => 'View Events'],
        'opportunities' => ['href' => 'youth_opportunities.php', 'icon' => 'fas fa-briefcase', 'text' => 'Opportunities'],
        'qrcode' => ['href' => 'youth_qr.php', 'icon' => 'fas fa-qrcode', 'text' => 'My QR Code'],
        'feedback' => ['href' => 'youth_feedback.php', 'icon' => 'fas fa-comment-dots', 'text' => 'Submit Feedback'],
        'profile' => ['href' => 'youth_profile.php', 'icon' => 'fas fa-user-circle', 'text' => 'My Profile'],
    ];
    
    // Get user info from session
    $sidebar_user_name = $_SESSION['user_name'] ?? "Youth User";
    $sidebar_user_role = "Youth Resident";
    $sidebar_user_initials = substr($sidebar_user_name, 0, 2);

    echo '<div class="flex flex-col w-64 bg-indigo-700 text-white shadow-lg h-full fixed left-0 top-0">';
    echo '    <div class="flex items-center justify-center h-16 border-b border-indigo-600">';
    echo '        <span class="text-2xl font-semibold tracking-wider">SK Youth Portal</span>';
    echo '    </div>';
    echo '    <nav class="flex-1 p-4 space-y-2 overflow-y-auto">';
    
    foreach ($nav_items as $data_page => $item) {
        $isActive = ($current_page_data_page === $data_page) ? 'bg-indigo-800 text-white font-medium' : 'hover:bg-indigo-600';

        echo '        <a href="' . $item['href'] . '" class="nav-item flex items-center p-3 rounded-lg ' . $isActive . ' transition duration-150" data-page="' . $data_page . '">';
        echo '            <i class="' . $item['icon'] . ' mr-3"></i> ' . $item['text'];
        echo '        </a>';
    }

    echo '    </nav>';
    
    // User Info Section
    echo '    <div class="p-4 border-t border-indigo-600">';
    echo '        <div class="flex items-center">';
    echo '            <img class="h-10 w-10 rounded-full object-cover border-2 border-white" src="https://ui-avatars.com/api/?name=' . urlencode($sidebar_user_initials) . '&background=fff&color=121C42&bold=true" alt="User Avatar">';
    echo '            <div class="ml-3 leading-tight">';
    echo '                <p class="text-sm font-semibold text-white">' . htmlspecialchars($sidebar_user_name) . '</p>';
    echo '                <p class="text-xs text-indigo-200">' . htmlspecialchars($sidebar_user_role) . '</p>';
    echo '            </div>';
    echo '            <a href="logout.php" class="ml-auto text-indigo-200 hover:text-white transition duration-150" title="Logout">';
    echo '                <i class="fas fa-sign-out-alt text-xl"></i>';
    echo '            </a>';
    echo '        </div>';
    echo '    </div>';
    
    echo '</div>';
}
?>