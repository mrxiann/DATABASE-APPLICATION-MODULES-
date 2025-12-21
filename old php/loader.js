// loader.js
// Note: This is now ONLY for static HTML pages that don't use PHP includes
// For PHP pages, use the PHP sidebar functions instead

document.addEventListener('DOMContentLoaded', () => {
    const sidebarContainer = document.getElementById('sidebar-container');
    
    if (!sidebarContainer) return; // Exit if no container found
    
    const currentPage = sidebarContainer.dataset.page;
    const userRole = sidebarContainer.dataset.role;
    
    // Determine the correct sidebar file to load
    const sidebarFile = (userRole === 'admin') ? 'admin_sidebar.html' : 'youth_sidebar.html';

    // 1. Fetch and inject the sidebar content
    fetch(sidebarFile)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to load ${sidebarFile}`);
            }
            return response.text();
        })
        .then(html => {
            sidebarContainer.innerHTML = html;
            
            // 2. Highlight the active link after injection
            setTimeout(() => {
                const activeLink = sidebarContainer.querySelector(`a[data-page="${currentPage}"]`);
                if (activeLink) {
                    // Remove any existing active classes
                    sidebarContainer.querySelectorAll('a').forEach(link => {
                        link.classList.remove('bg-blue-800', 'bg-indigo-800', 'text-white');
                    });
                    
                    // Add appropriate active class
                    if (userRole === 'admin') {
                        activeLink.classList.add('bg-blue-800', 'text-white');
                    } else {
                        activeLink.classList.add('bg-indigo-800', 'text-white');
                    }
                }
            }, 100);
        })
        .catch(error => console.error(`Error loading ${sidebarFile}:`, error));
});