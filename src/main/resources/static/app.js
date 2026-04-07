const signupForm = document.getElementById("signupForm");
const loginForm = document.getElementById("loginForm");
const otpForm = document.getElementById("otpForm");
const profileForm = document.getElementById("profileForm");
const passwordForm = document.getElementById("passwordForm");
const resetPasswordOtpForm = document.getElementById("resetPasswordOtpForm");
const signupFeedback = document.getElementById("signupFeedback");
const loginFeedback = document.getElementById("loginFeedback");
const otpFeedback = document.getElementById("otpFeedback");
const profileFeedback = document.getElementById("profileFeedback");
const passwordFeedback = document.getElementById("passwordFeedback");
const resetPasswordFeedback = document.getElementById("resetPasswordFeedback");
const logoutButton = document.getElementById("logoutButton");
const topbarLogoutButton = document.getElementById("topbarLogoutButton");
const plotsGrid = document.getElementById("plotsGrid");
const plotsEmpty = document.getElementById("plotsEmpty");
const myBookingsGrid = document.getElementById("myBookingsGrid");
const myBookingsEmpty = document.getElementById("myBookingsEmpty");
const paymentsGrid = document.getElementById("paymentsGrid");
const paymentsEmpty = document.getElementById("paymentsEmpty");
const favoritesGrid = document.getElementById("favoritesGrid");
const favoritesEmpty = document.getElementById("favoritesEmpty");
const notificationsGrid = document.getElementById("notificationsGrid");
const notificationsEmpty = document.getElementById("notificationsEmpty");
const userCount = document.getElementById("userCount");
const pulseFill = document.getElementById("pulseFill");
const panelNote = document.getElementById("panelNote");
const lastAction = document.getElementById("lastAction");
const currentUser = document.getElementById("currentUser");
const plotCount = document.getElementById("plotCount");
const myBookingCount = document.getElementById("myBookingCount");
const paymentCount = document.getElementById("paymentCount");
const favoriteCount = document.getElementById("favoriteCount");
const notificationCount = document.getElementById("notificationCount");
const heroSearchInput = document.getElementById("heroSearchInput");
const heroSearchButton = document.getElementById("heroSearchButton");
const profileDisplayName = document.getElementById("profileDisplayName");
const profileDisplayEmail = document.getElementById("profileDisplayEmail");
const metricTotalUsers = document.getElementById("metricTotalUsers");
const metricAdmins = document.getElementById("metricAdmins");
const metricInvestors = document.getElementById("metricInvestors");
const metricRegularUsers = document.getElementById("metricRegularUsers");
const metricApprovedPlots = document.getElementById("metricApprovedPlots");
const metricPendingPlots = document.getElementById("metricPendingPlots");
const metricRejectedPlots = document.getElementById("metricRejectedPlots");
const metricReservedPlots = document.getElementById("metricReservedPlots");
const metricBookings = document.getElementById("metricBookings");
const metricPayments = document.getElementById("metricPayments");
const metricPaymentSuccess = document.getElementById("metricPaymentSuccess");
const metricPaymentFailed = document.getElementById("metricPaymentFailed");
const rolesSection = document.getElementById("rolesSection");
const publicSections = document.querySelectorAll(".public-home");
const accessSection = document.getElementById("accessSection");
const protectedSections = document.querySelectorAll(".requires-auth");
const refreshPlotsButton = document.getElementById("refreshPlots");
const plotSearchInput = document.getElementById("plotSearch");
const plotFilterStatus = document.getElementById("plotFilterStatus");
const clearPlotFiltersButton = document.getElementById("clearPlotFilters");
const tabButtons = document.querySelectorAll(".tab-button");
const authFormPanels = document.querySelectorAll("[data-auth-panel]");
const plotForm = document.getElementById("plotForm");
const plotFeedback = document.getElementById("plotFeedback");
const plotFormTitle = document.getElementById("plotFormTitle");
const plotSubmitButton = document.getElementById("plotSubmitButton");
const cancelEditButton = document.getElementById("cancelEditButton");
const editingPlotIdInput = document.getElementById("editingPlotId");
const directoryLastAction = document.getElementById("directoryLastAction");
const directoryCurrentUser = document.getElementById("directoryCurrentUser");
const plotInventoryCount = document.getElementById("plotInventoryCount");
const pendingApprovalCount = document.getElementById("pendingApprovalCount");
const adminDashboard = document.getElementById("adminDashboard");
const buyerDashboard = document.getElementById("buyerDashboard");
const dashboardWelcomeCopy = document.getElementById("dashboardWelcomeCopy");
const buyerRoleLabel = document.getElementById("buyerRoleLabel");
const plotDetailsModal = document.getElementById("plotDetailsModal");
const closePlotDetailsButton = document.getElementById("closePlotDetails");
const plotDetailsMedia = document.getElementById("plotDetailsMedia");
const plotDetailsTitle = document.getElementById("plotDetailsTitle");
const plotDetailsStatus = document.getElementById("plotDetailsStatus");
const plotDetailsPrice = document.getElementById("plotDetailsPrice");
const plotDetailsLocation = document.getElementById("plotDetailsLocation");
const detailPlotNumber = document.getElementById("detailPlotNumber");
const detailArea = document.getElementById("detailArea");
const detailOwner = document.getElementById("detailOwner");
const detailBookedBy = document.getElementById("detailBookedBy");
const detailApprovalStatus = document.getElementById("detailApprovalStatus");
const plotDetailsSummary = document.getElementById("plotDetailsSummary");
const plotDetailsInvestorNote = document.getElementById("plotDetailsInvestorNote");
const modalBookButton = document.getElementById("modalBookButton");
const modalFavoriteButton = document.getElementById("modalFavoriteButton");
const modalEditButton = document.getElementById("modalEditButton");
const modalDeleteButton = document.getElementById("modalDeleteButton");
const requestOtpButton = document.getElementById("requestOtpButton");
const requestSignupOtpButton = document.getElementById("requestSignupOtpButton");
const requestResetOtpButton = document.getElementById("requestResetOtpButton");
const uploadImageButton = document.getElementById("uploadImageButton");
const plotImageFileInput = document.getElementById("plotImageFile");
const paymentModal = document.getElementById("paymentModal");
const closePaymentModalButton = document.getElementById("closePaymentModal");
const paymentForm = document.getElementById("paymentForm");
const paymentMethod = document.getElementById("paymentMethod");
const paymentAmount = document.getElementById("paymentAmount");
const paymentPlotSummary = document.getElementById("paymentPlotSummary");
const paymentFeedback = document.getElementById("paymentFeedback");
const cancelPaymentButton = document.getElementById("cancelPaymentButton");
const exportAdminReportButton = document.getElementById("exportAdminReportButton");
const profileNameInput = document.getElementById("profileName");
const profilePhoneInput = document.getElementById("profilePhone");
const currentPasswordInput = document.getElementById("currentPassword");
const newPasswordInput = document.getElementById("newPassword");
const resetRoleInput = document.getElementById("resetRole");
const resetEmailInput = document.getElementById("resetEmail");
const resetOtpCodeInput = document.getElementById("resetOtpCode");
const resetNewPasswordInput = document.getElementById("resetNewPassword");

const state = {
    currentUser: null,
    token: localStorage.getItem("smartplot_token"),
    totalUsers: 0,
    totalPlots: 0,
    myBookings: 0,
    totalPayments: 0,
    totalNotifications: 0,
    analytics: null,
    profile: null,
    favoritePlotIds: [],
    plotSearch: "",
    plotStatus: "ALL",
    selectedPlot: null,
    paymentPlot: null
};

tabButtons.forEach((button) => {
    button.addEventListener("click", () => {
        const activeTab = button.dataset.tab;

        tabButtons.forEach((tab) => {
            tab.classList.toggle("active", tab === button);
        });

        authFormPanels.forEach((panel) => {
            panel.classList.toggle("active", panel.id === `${activeTab}Form`);
        });

        clearFeedback();
    });
});

signupForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    setFeedback(signupFeedback, "Verifying signup OTP...", "");

    try {
        const response = await request("/signup/verify-otp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: document.getElementById("signupRole").value,
                email: document.getElementById("signupEmail").value.trim(),
                otpCode: document.getElementById("signupOtpCode").value.trim()
            })
        });

        signupForm.reset();
        document.getElementById("signupRole").value = "USER";
        handleAuthSuccess(response);
        lastAction.textContent = `Signed up ${response.user.name} as ${response.user.role}`;
        directoryLastAction.textContent = `Signed up ${response.user.name} as ${response.user.role}`;
        setFeedback(signupFeedback, "Account created successfully.", "success");
        await loadProtectedData();
    } catch (error) {
        setFeedback(signupFeedback, error.message, "error");
        lastAction.textContent = "Signup failed";
        directoryLastAction.textContent = "Signup failed";
    }
});

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    setFeedback(loginFeedback, "Checking credentials...", "");

    const payload = {
        role: document.getElementById("loginRole").value,
        email: document.getElementById("loginEmail").value.trim(),
        password: document.getElementById("loginPassword").value
    };

    try {
        const response = await request("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        loginForm.reset();
        document.getElementById("loginRole").value = "USER";
        handleAuthSuccess(response);
        lastAction.textContent = `${response.message} as ${response.user.role}`;
        directoryLastAction.textContent = `${response.message} as ${response.user.role}`;
        setFeedback(loginFeedback, `${response.message}. Welcome back, ${response.user.name}.`, "success");
        await loadProtectedData();
    } catch (error) {
        setFeedback(loginFeedback, error.message, "error");
        lastAction.textContent = "Login failed";
        directoryLastAction.textContent = "Login failed";
    }
});

otpForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    setFeedback(otpFeedback, "Verifying OTP...", "");

    try {
        const response = await request("/login/verify-otp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: document.getElementById("otpRole").value,
                email: document.getElementById("otpEmail").value.trim(),
                otpCode: document.getElementById("otpCode").value.trim()
            })
        });

        otpForm.reset();
        document.getElementById("otpRole").value = "USER";
        handleAuthSuccess(response);
        lastAction.textContent = `${response.message} as ${response.user.role}`;
        directoryLastAction.textContent = `${response.message} as ${response.user.role}`;
        setFeedback(otpFeedback, `${response.message}. Welcome, ${response.user.name}.`, "success");
        await loadProtectedData();
    } catch (error) {
        setFeedback(otpFeedback, error.message, "error");
        lastAction.textContent = "OTP login failed";
        directoryLastAction.textContent = "OTP login failed";
    }
});

profileForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    if (!state.token) {
        setFeedback(profileFeedback, "Log in first to update your profile.", "error");
        return;
    }

    setFeedback(profileFeedback, "Saving profile...", "");

    try {
        const profile = await request("/me", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: profileNameInput.value.trim(),
                phone: profilePhoneInput.value.trim()
            })
        });

        state.profile = profile;
        state.currentUser = profile;
        localStorage.setItem("smartplot_user", JSON.stringify(profile));
        currentUser.textContent = `${profile.name} (${profile.role})`;
        directoryCurrentUser.textContent = `${profile.name} (${profile.role})`;
        renderProfile(profile);
        setFeedback(profileFeedback, "Profile updated successfully.", "success");
        lastAction.textContent = "Profile updated";
        directoryLastAction.textContent = "Profile updated";
        await loadUsers();
        await loadNotifications();
    } catch (error) {
        setFeedback(profileFeedback, error.message, "error");
    }
});

passwordForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    if (!state.token) {
        setFeedback(passwordFeedback, "Log in first to change your password.", "error");
        return;
    }

    setFeedback(passwordFeedback, "Changing password...", "");

    try {
        const response = await request("/me/change-password", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                currentPassword: currentPasswordInput.value,
                newPassword: newPasswordInput.value
            })
        });

        passwordForm.reset();
        setFeedback(passwordFeedback, response.message, "success");
        lastAction.textContent = "Password changed";
        directoryLastAction.textContent = "Password changed";
        await loadNotifications();
    } catch (error) {
        setFeedback(passwordFeedback, error.message, "error");
    }
});

resetPasswordOtpForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    setFeedback(resetPasswordFeedback, "Resetting password...", "");

    try {
        const response = await request("/login/reset-password", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: resetRoleInput.value,
                email: resetEmailInput.value.trim(),
                otpCode: resetOtpCodeInput.value.trim(),
                newPassword: resetNewPasswordInput.value
            })
        });

        resetPasswordOtpForm.reset();
        resetRoleInput.value = "USER";
        setFeedback(resetPasswordFeedback, response.message, "success");
        lastAction.textContent = "Password reset with OTP";
        directoryLastAction.textContent = "Password reset with OTP";
        await loadNotifications();
    } catch (error) {
        setFeedback(resetPasswordFeedback, error.message, "error");
        lastAction.textContent = "Password reset failed";
        directoryLastAction.textContent = "Password reset failed";
    }
});

refreshPlotsButton.addEventListener("click", async () => {
    lastAction.textContent = "Refreshing plots";
    directoryLastAction.textContent = "Refreshing plots";
    await loadPlots();
    await loadAdminAnalytics();
});

exportAdminReportButton.addEventListener("click", async () => {
    if (!isAdmin()) {
        return;
    }

    try {
        const blob = await requestBlob("/admin/reports/summary.csv");
        const url = window.URL.createObjectURL(blob);
        const anchor = document.createElement("a");
        anchor.href = url;
        anchor.download = "smartplot-admin-summary.csv";
        document.body.appendChild(anchor);
        anchor.click();
        anchor.remove();
        window.URL.revokeObjectURL(url);
        lastAction.textContent = "Admin report downloaded";
        directoryLastAction.textContent = "Admin report downloaded";
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = "Admin report download failed";
        directoryLastAction.textContent = "Admin report download failed";
    }
});

heroSearchButton.addEventListener("click", () => {
    const query = heroSearchInput.value.trim();
    if (query) {
        plotSearchInput.value = query;
        state.plotSearch = query;
    }
    accessSection.scrollIntoView({ behavior: "smooth", block: "start" });
});

function performLogout() {
    state.token = null;
    state.currentUser = null;
    state.profile = null;
    localStorage.removeItem("smartplot_token");
    localStorage.removeItem("smartplot_user");
    currentUser.textContent = "None";
    directoryCurrentUser.textContent = "None";
    renderProfile(null);
    logoutButton.classList.add("hidden");
    topbarLogoutButton.classList.add("hidden");
    lastAction.textContent = "Logged out";
    directoryLastAction.textContent = "Logged out";
    closePlotDetails();
    closePaymentModal();
    resetProtectedViews("Sign in to explore listings, favorites, and your bookings.");
}

logoutButton.addEventListener("click", performLogout);
topbarLogoutButton.addEventListener("click", performLogout);

closePlotDetailsButton.addEventListener("click", () => {
    closePlotDetails();
});

plotDetailsModal.addEventListener("click", (event) => {
    if (event.target.dataset.closeModal === "true") {
        closePlotDetails();
    }
});

closePaymentModalButton.addEventListener("click", () => {
    closePaymentModal();
});

paymentModal.addEventListener("click", (event) => {
    if (event.target.dataset.closePayment === "true") {
        closePaymentModal();
    }
});

cancelPaymentButton.addEventListener("click", () => {
    closePaymentModal();
});

paymentForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    if (!state.paymentPlot) {
        setFeedback(paymentFeedback, "Choose a plot before starting payment.", "error");
        return;
    }

    const selectedOutcome = paymentForm.querySelector('input[name="paymentOutcome"]:checked');
    const simulateSuccess = selectedOutcome && selectedOutcome.value === "success";

    setFeedback(paymentFeedback, "Processing mock payment...", "");

    try {
        const response = await request(`/plots/${state.paymentPlot.plotId}/payment`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                paymentMethod: paymentMethod.value,
                simulateSuccess
            })
        });

        if (response.paymentStatus === "SUCCESS") {
            lastAction.textContent = `Payment successful for ${response.plotNumber}`;
            directoryLastAction.textContent = lastAction.textContent;
            setFeedback(paymentFeedback, `${response.message}. Ref: ${response.transactionReference}`, "success");
            closePaymentModal();
            closePlotDetails();
            await loadPlots();
            await loadMyBookings();
            await loadPayments();
            await loadNotifications();
            await loadAdminAnalytics();
        } else {
            lastAction.textContent = `Payment failed for ${response.plotNumber}`;
            directoryLastAction.textContent = lastAction.textContent;
            setFeedback(paymentFeedback, `${response.message}. Ref: ${response.transactionReference}`, "error");
            await loadPayments();
            await loadNotifications();
            await loadAdminAnalytics();
        }
    } catch (error) {
        setFeedback(paymentFeedback, error.message, "error");
        lastAction.textContent = "Payment failed";
        directoryLastAction.textContent = "Payment failed";
    }
});

plotSearchInput.addEventListener("input", async (event) => {
    state.plotSearch = event.target.value.trim();
    await loadPlots();
});

plotFilterStatus.addEventListener("change", async (event) => {
    state.plotStatus = event.target.value;
    await loadPlots();
});

clearPlotFiltersButton.addEventListener("click", async () => {
    state.plotSearch = "";
    state.plotStatus = "ALL";
    plotSearchInput.value = "";
    plotFilterStatus.value = "ALL";
    await loadPlots();
});

requestOtpButton.addEventListener("click", async () => {
    setFeedback(otpFeedback, "Sending OTP...", "");

    try {
        const response = await request("/login/request-otp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: document.getElementById("otpRole").value,
                email: document.getElementById("otpEmail").value.trim()
            })
        });
        setFeedback(otpFeedback, response.message, "success");
        lastAction.textContent = "OTP sent";
        directoryLastAction.textContent = "OTP sent";
    } catch (error) {
        setFeedback(otpFeedback, error.message, "error");
        lastAction.textContent = "OTP request failed";
        directoryLastAction.textContent = "OTP request failed";
    }
});

requestSignupOtpButton.addEventListener("click", async () => {
    setFeedback(signupFeedback, "Sending signup OTP...", "");

    try {
        const response = await request("/signup/request-otp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: document.getElementById("signupRole").value,
                name: document.getElementById("signupName").value.trim(),
                email: document.getElementById("signupEmail").value.trim(),
                password: document.getElementById("signupPassword").value,
                phone: document.getElementById("signupPhone").value.trim()
            })
        });
        setFeedback(signupFeedback, response.message, "success");
        lastAction.textContent = "Signup OTP sent";
        directoryLastAction.textContent = "Signup OTP sent";
    } catch (error) {
        setFeedback(signupFeedback, error.message, "error");
        lastAction.textContent = "Signup OTP request failed";
        directoryLastAction.textContent = "Signup OTP request failed";
    }
});

requestResetOtpButton.addEventListener("click", async () => {
    setFeedback(resetPasswordFeedback, "Sending reset OTP...", "");

    try {
        const response = await request("/login/request-password-reset", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                role: resetRoleInput.value,
                email: resetEmailInput.value.trim()
            })
        });
        setFeedback(resetPasswordFeedback, response.message, "success");
        lastAction.textContent = "Reset OTP sent";
        directoryLastAction.textContent = "Reset OTP sent";
    } catch (error) {
        setFeedback(resetPasswordFeedback, error.message, "error");
        lastAction.textContent = "Reset OTP request failed";
        directoryLastAction.textContent = "Reset OTP request failed";
    }
});

cancelEditButton.addEventListener("click", () => {
    resetPlotForm();
});

uploadImageButton.addEventListener("click", async () => {
    await uploadSelectedImage();
});

plotForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    if (!isAdmin()) {
        setFeedback(plotFeedback, "Only ADMIN accounts can manage plot listings.", "error");
        return;
    }

    const editingPlotId = editingPlotIdInput.value;
    const isEditing = Boolean(editingPlotId);
    setFeedback(plotFeedback, isEditing ? "Updating plot..." : "Creating plot...", "");

    const payload = {
        plotNumber: document.getElementById("plotNumber").value.trim(),
        ownerName: document.getElementById("ownerName").value.trim(),
        location: document.getElementById("plotLocation").value.trim(),
        areaSqft: Number(document.getElementById("areaSqft").value),
        price: Number(document.getElementById("plotPrice").value),
        status: document.getElementById("plotStatus").value,
        imageUrl: document.getElementById("plotImageUrl").value.trim()
    };

    try {
        const plot = await request(isEditing ? `/plots/${editingPlotId}` : "/plots", {
            method: isEditing ? "PUT" : "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        lastAction.textContent = isEditing
            ? `Updated plot ${plot.plotNumber}`
            : `Created plot ${plot.plotNumber}`;
        directoryLastAction.textContent = lastAction.textContent;
        setFeedback(
            plotFeedback,
            isEditing ? "Plot updated successfully." : "Plot created successfully.",
            "success"
        );
        resetPlotForm();
        await loadPlots();
        await loadMyBookings();
        await loadAdminAnalytics();
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = isEditing ? "Plot update failed" : "Plot creation failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
});

async function loadUsers() {
    if (!state.token || !isAdmin()) {
        state.totalUsers = 0;
        updatePortalPulse();
        return;
    }

    try {
        const users = await request("/users");
        state.totalUsers = users.length;
        updatePortalPulse();
    } catch (error) {
        state.totalUsers = 0;
        updatePortalPulse();
    }
}

async function loadPlots() {
    if (!state.token) {
        state.totalPlots = 0;
        plotsGrid.innerHTML = "";
        plotsEmpty.hidden = false;
        plotsEmpty.querySelector("h3").textContent = "Login required";
        plotsEmpty.querySelector("p").textContent = "Sign in to manage plot inventory.";
        updatePortalPulse();
        return;
    }

    try {
        const params = new URLSearchParams();
        if (state.plotSearch) {
            params.set("search", state.plotSearch);
        }
        if (state.plotStatus && state.plotStatus !== "ALL") {
            params.set("status", state.plotStatus);
        }

        const query = params.toString();
        const plots = await request(query ? `/plots?${query}` : "/plots");
        state.totalPlots = plots.length;
        renderPlots(plots);
        updatePortalPulse();
    } catch (error) {
        plotsGrid.innerHTML = "";
        plotsEmpty.hidden = false;
        plotsEmpty.querySelector("h3").textContent = "Could not load plots";
        plotsEmpty.querySelector("p").textContent = error.message;
        state.totalPlots = 0;
        updatePortalPulse();
    }
}

async function loadMyBookings() {
    if (!state.token) {
        state.myBookings = 0;
        myBookingsGrid.innerHTML = "";
        myBookingsEmpty.hidden = false;
        myBookingsEmpty.querySelector("h3").textContent = "Login required";
        myBookingsEmpty.querySelector("p").textContent = "Sign in to view your booked plots.";
        updatePortalPulse();
        return;
    }

    try {
        const bookings = await request("/plots/my-bookings");
        state.myBookings = bookings.length;
        renderMyBookings(bookings);
        updatePortalPulse();
    } catch (error) {
        myBookingsGrid.innerHTML = "";
        myBookingsEmpty.hidden = false;
        myBookingsEmpty.querySelector("h3").textContent = "Could not load bookings";
        myBookingsEmpty.querySelector("p").textContent = error.message;
        state.myBookings = 0;
        updatePortalPulse();
    }
}

async function loadPayments() {
    if (!state.token) {
        state.totalPayments = 0;
        paymentsGrid.innerHTML = "";
        paymentsEmpty.hidden = false;
        paymentsEmpty.querySelector("h3").textContent = "Login required";
        paymentsEmpty.querySelector("p").textContent = "Sign in to view your payment activity.";
        updatePortalPulse();
        return;
    }

    if (isAdmin()) {
        state.totalPayments = 0;
        paymentsGrid.innerHTML = "";
        paymentsEmpty.hidden = false;
        paymentsEmpty.querySelector("h3").textContent = "Buyer-only section";
        paymentsEmpty.querySelector("p").textContent = "Payment activity appears here for home buyers and investors.";
        updatePortalPulse();
        return;
    }

    try {
        const payments = await request("/payments/my");
        state.totalPayments = payments.length;
        renderPayments(payments);
        updatePortalPulse();
    } catch (error) {
        paymentsGrid.innerHTML = "";
        paymentsEmpty.hidden = false;
        paymentsEmpty.querySelector("h3").textContent = "Could not load payments";
        paymentsEmpty.querySelector("p").textContent = error.message;
        state.totalPayments = 0;
        updatePortalPulse();
    }
}

async function loadFavorites() {
    if (!state.token) {
        state.favoritePlotIds = [];
        favoritesGrid.innerHTML = "";
        favoritesEmpty.hidden = false;
        favoritesEmpty.querySelector("h3").textContent = "Login required";
        favoritesEmpty.querySelector("p").textContent = "Sign in to save favorite plots.";
        favoriteCount.textContent = "0";
        return;
    }

    if (isAdmin()) {
        state.favoritePlotIds = [];
        favoritesGrid.innerHTML = "";
        favoritesEmpty.hidden = false;
        favoritesEmpty.querySelector("h3").textContent = "Buyer-only section";
        favoritesEmpty.querySelector("p").textContent = "Saved plots are available here for home buyers and investors.";
        favoriteCount.textContent = "0";
        return;
    }

    try {
        const favorites = await request("/plots/favorites");
        state.favoritePlotIds = favorites.map((plot) => plot.plotId);
        renderFavorites(favorites);
    } catch (error) {
        state.favoritePlotIds = [];
        favoritesGrid.innerHTML = "";
        favoritesEmpty.hidden = false;
        favoritesEmpty.querySelector("h3").textContent = "Could not load favorites";
        favoritesEmpty.querySelector("p").textContent = error.message;
        favoriteCount.textContent = "0";
    }
}

async function loadNotifications() {
    if (!state.token) {
        state.totalNotifications = 0;
        notificationsGrid.innerHTML = "";
        notificationsEmpty.hidden = false;
        notificationsEmpty.querySelector("h3").textContent = "Login required";
        notificationsEmpty.querySelector("p").textContent = "Sign in to view recent notifications.";
        notificationCount.textContent = "0";
        return;
    }

    try {
        const notifications = await request("/notifications/my");
        state.totalNotifications = notifications.length;
        renderNotifications(notifications);
    } catch (error) {
        state.totalNotifications = 0;
        notificationsGrid.innerHTML = "";
        notificationsEmpty.hidden = false;
        notificationsEmpty.querySelector("h3").textContent = "Could not load notifications";
        notificationsEmpty.querySelector("p").textContent = error.message;
        notificationCount.textContent = "0";
    }
}

async function loadProfile() {
    if (!state.token) {
        state.profile = null;
        renderProfile(null);
        return;
    }

    try {
        const profile = await request("/me");
        state.profile = profile;
        state.currentUser = profile;
        localStorage.setItem("smartplot_user", JSON.stringify(profile));
        currentUser.textContent = `${profile.name} (${profile.role})`;
        directoryCurrentUser.textContent = `${profile.name} (${profile.role})`;
        renderProfile(profile);
    } catch (error) {
        state.profile = null;
        renderProfile(null);
    }
}

async function loadAdminAnalytics() {
    if (!state.token || !isAdmin()) {
        state.analytics = null;
        renderAdminAnalytics(null);
        return;
    }

    try {
        const analytics = await request("/admin/analytics");
        state.analytics = analytics;
        renderAdminAnalytics(analytics);
    } catch (error) {
        state.analytics = null;
        renderAdminAnalytics(null);
        lastAction.textContent = "Analytics load failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
}

function renderPlots(plots) {
    plotsGrid.innerHTML = "";
    plotsEmpty.hidden = plots.length > 0;
    plotInventoryCount.textContent = String(plots.length);
    plotCount.textContent = `${plots.length} plots`;
    pendingApprovalCount.textContent = String(plots.filter((plot) => (plot.approvalStatus || "PENDING") === "PENDING").length);

    plots.forEach((plot, index) => {
        const card = document.createElement("article");
        card.className = "plot-card listing-result-card";
        card.style.animationDelay = `${index * 70}ms`;
        const statusClass = plot.status.toLowerCase();
        const approvalStatus = plot.approvalStatus || "PENDING";
        const approvalClass = approvalStatus.toLowerCase();

        const canBook = plot.status === "AVAILABLE" && approvalStatus === "APPROVED" && !isAdmin();
        const isFavorite = state.favoritePlotIds.includes(plot.plotId);
        const canManage = isAdmin();
        const canApprove = canManage && approvalStatus !== "APPROVED";
        const canReject = canManage && approvalStatus !== "REJECTED" && plot.status !== "RESERVED" && plot.status !== "SOLD";
        const pricePerSqft = Number(plot.areaSqft) > 0 ? Math.round(Number(plot.price) / Number(plot.areaSqft)) : 0;
        const title = buildListingTitle(plot);
        const highlightOne = approvalStatus === "APPROVED" ? "Approved listing" : "Pending review";
        const highlightTwo = plot.status === "AVAILABLE" ? "Ready to reserve" : `Currently ${plot.status.toLowerCase()}`;

        card.innerHTML = `
            <div class="listing-result-media">
                ${renderPlotMedia(plot)}
            </div>
            <div class="listing-result-body">
                <div class="plot-topline">
                    <h3>${escapeHtml(title)}</h3>
                    <span class="plot-status ${approvalClass}">${escapeHtml(approvalStatus)}</span>
                </div>
                <div class="listing-location-row">
                    <span>${escapeHtml(plot.ownerName)}</span>
                    <span>•</span>
                    <span>${escapeHtml(plot.location)}</span>
                </div>
                <div class="listing-spec-row">
                    <div class="listing-spec">
                        <span class="mini-label">Plot Area</span>
                        <strong>${escapeHtml(plot.areaSqft)} sqft</strong>
                    </div>
                    <div class="listing-spec">
                        <span class="mini-label">Availability</span>
                        <strong>${escapeHtml(plot.status)}</strong>
                    </div>
                    <div class="listing-spec">
                        <span class="mini-label">Plot Number</span>
                        <strong>${escapeHtml(plot.plotNumber)}</strong>
                    </div>
                </div>
                <div class="listing-highlights">
                    <span class="listing-highlight">${escapeHtml(highlightOne)}</span>
                    <span class="listing-highlight">${escapeHtml(highlightTwo)}</span>
                    ${plot.bookedByName ? `<span class="listing-highlight">Booked by ${escapeHtml(plot.bookedByName)}</span>` : ""}
                </div>
            </div>
            <div class="listing-result-actions">
                <div class="listing-price-panel">
                    <strong>${formatCurrency(plot.price)}</strong>
                    <span>${pricePerSqft > 0 ? `${formatCompactCurrency(pricePerSqft)} per sqft` : "Price available on request"}</span>
                </div>
                <div class="stacked-actions">
                    <button class="primary-button no-margin" type="button" data-action="details">View Details</button>
                    ${canBook ? '<button class="ghost-button" type="button" data-action="book">Reserve Plot</button>' : ""}
                    ${!isAdmin() && approvalStatus === "APPROVED" ? `<button class="ghost-button" type="button" data-action="favorite">${isFavorite ? "Saved to Shortlist" : "Save to Shortlist"}</button>` : ""}
                    ${canApprove ? '<button class="card-action approve" type="button" data-action="approve">Approve Listing</button>' : ""}
                    ${canReject ? '<button class="card-action reject" type="button" data-action="reject">Reject Listing</button>' : ""}
                    ${canManage ? '<button class="card-action edit" type="button" data-action="edit">Edit Listing</button>' : ""}
                    ${canManage ? '<button class="card-action delete" type="button" data-action="delete">Delete Listing</button>' : ""}
                </div>
            </div>
        `;

        const bookButton = card.querySelector('[data-action="book"]');
        if (bookButton) {
            bookButton.addEventListener("click", async () => {
                await bookPlot(plot);
            });
        }

        card.querySelector('[data-action="details"]').addEventListener("click", () => {
            openPlotDetails(plot);
        });

        const approveButton = card.querySelector('[data-action="approve"]');
        if (approveButton) {
            approveButton.addEventListener("click", async () => {
                await approvePlot(plot);
            });
        }

        const rejectButton = card.querySelector('[data-action="reject"]');
        if (rejectButton) {
            rejectButton.addEventListener("click", async () => {
                await rejectPlot(plot);
            });
        }

        const favoriteButton = card.querySelector('[data-action="favorite"]');
        if (favoriteButton) {
            favoriteButton.addEventListener("click", async () => {
                await toggleFavorite(plot);
            });
        }

        const editButton = card.querySelector('[data-action="edit"]');
        if (editButton) {
            editButton.addEventListener("click", () => {
                startEditingPlot(plot);
            });
        }

        const deleteButton = card.querySelector('[data-action="delete"]');
        if (deleteButton) {
            deleteButton.addEventListener("click", async () => {
                await deletePlot(plot);
            });
        }

        plotsGrid.appendChild(card);
    });
}

function buildListingTitle(plot) {
    return `Residential Plot in ${plot.location}`;
}

function renderMyBookings(bookings) {
    myBookingsGrid.innerHTML = "";
    myBookingsEmpty.hidden = bookings.length > 0;
    myBookingCount.textContent = String(bookings.length);

    bookings.forEach((plot, index) => {
        const card = document.createElement("article");
        card.className = "plot-card";
        card.style.animationDelay = `${index * 70}ms`;
        card.innerHTML = `
            ${renderPlotMedia(plot)}
            <div class="plot-card-content">
                <div class="plot-topline">
                    <h3>${escapeHtml(plot.plotNumber)}</h3>
                    <span class="plot-status ${plot.status.toLowerCase()}">${escapeHtml(plot.status)}</span>
                </div>
                <div class="plot-meta">
                    <span><strong>Location:</strong> ${escapeHtml(plot.location)}</span>
                    <span><strong>Area:</strong> ${escapeHtml(plot.areaSqft)} sqft</span>
                    <span><strong>Owner:</strong> ${escapeHtml(plot.ownerName)}</span>
                </div>
                <div class="plot-price">${formatCurrency(plot.price)}</div>
                <div class="booking-note">Reserved under your account.</div>
                <div class="plot-actions">
                    <button class="card-action cancel" type="button" data-action="cancel-booking">Cancel Booking</button>
                </div>
            </div>
        `;
        card.querySelector('[data-action="cancel-booking"]').addEventListener("click", async () => {
            await cancelBooking(plot);
        });
        myBookingsGrid.appendChild(card);
    });
}

function renderPayments(payments) {
    paymentsGrid.innerHTML = "";
    paymentsEmpty.hidden = payments.length > 0;
    paymentCount.textContent = String(payments.length);

    payments.forEach((payment, index) => {
        const card = document.createElement("article");
        card.className = "user-card payment-card";
        card.style.animationDelay = `${index * 70}ms`;
        const statusClass = payment.paymentStatus === "SUCCESS" ? "available" : "sold";
        const createdAt = payment.createdAt
            ? new Date(payment.createdAt).toLocaleString("en-IN")
            : "Just now";

        card.innerHTML = `
            <div class="plot-topline">
                <h3>${escapeHtml(payment.plotNumber)}</h3>
                <span class="plot-status ${statusClass}">${escapeHtml(payment.paymentStatus)}</span>
            </div>
            <div class="user-meta">
                <span><strong>Amount:</strong> ${formatCurrency(payment.amount)}</span>
                <span><strong>Method:</strong> ${escapeHtml(payment.paymentMethod)}</span>
                <span><strong>Ref:</strong> ${escapeHtml(payment.transactionReference)}</span>
                <span><strong>Time:</strong> ${escapeHtml(createdAt)}</span>
            </div>
        `;
        paymentsGrid.appendChild(card);
    });
}

function renderFavorites(favorites) {
    favoritesGrid.innerHTML = "";
    favoritesEmpty.hidden = favorites.length > 0;
    favoriteCount.textContent = String(favorites.length);

    favorites.forEach((plot, index) => {
        const card = document.createElement("article");
        card.className = "plot-card";
        card.style.animationDelay = `${index * 70}ms`;
        card.innerHTML = `
            ${renderPlotMedia(plot)}
            <div class="plot-card-content">
                <div class="plot-topline">
                    <h3>${escapeHtml(plot.plotNumber)}</h3>
                    <span class="plot-status ${plot.status.toLowerCase()}">${escapeHtml(plot.status)}</span>
                </div>
                <div class="plot-meta">
                    <span><strong>Location:</strong> ${escapeHtml(plot.location)}</span>
                    <span><strong>Area:</strong> ${escapeHtml(plot.areaSqft)} sqft</span>
                    <span><strong>Owner:</strong> ${escapeHtml(plot.ownerName)}</span>
                </div>
                <div class="plot-price">${formatCurrency(plot.price)}</div>
                <div class="plot-actions">
                    <button class="card-action details" type="button" data-action="details">View Details</button>
                    <button class="card-action favorite" type="button" data-action="favorite">Remove</button>
                </div>
            </div>
        `;
        card.querySelector('[data-action="details"]').addEventListener("click", () => {
            openPlotDetails(plot);
        });
        card.querySelector('[data-action="favorite"]').addEventListener("click", async () => {
            await toggleFavorite(plot);
        });
        favoritesGrid.appendChild(card);
    });
}

function renderNotifications(notifications) {
    notificationsGrid.innerHTML = "";
    notificationsEmpty.hidden = notifications.length > 0;
    notificationCount.textContent = String(notifications.length);

    notifications.forEach((notification, index) => {
        const card = document.createElement("article");
        card.className = "user-card notification-card";
        card.style.animationDelay = `${index * 70}ms`;
        const createdAt = notification.createdAt
            ? new Date(notification.createdAt).toLocaleString("en-IN")
            : "Just now";

        card.innerHTML = `
            <div class="plot-topline">
                <h3>${escapeHtml(notification.title)}</h3>
                <span class="plot-status ${notification.read ? "approved" : "pending"}">${notification.read ? "READ" : "NEW"}</span>
            </div>
            <div class="user-meta">
                <span><strong>Type:</strong> ${escapeHtml(notification.type)}</span>
                <span>${escapeHtml(notification.message)}</span>
                <span><strong>Time:</strong> ${escapeHtml(createdAt)}</span>
            </div>
            ${notification.read ? "" : '<div class="plot-actions"><button class="card-action details" type="button" data-action="mark-read">Mark Read</button></div>'}
        `;

        const markReadButton = card.querySelector('[data-action="mark-read"]');
        if (markReadButton) {
            markReadButton.addEventListener("click", async () => {
                await markNotificationRead(notification.notificationId);
            });
        }

        notificationsGrid.appendChild(card);
    });
}

function renderProfile(profile) {
    if (!profile) {
        profileDisplayName.textContent = "Login required";
        profileDisplayEmail.textContent = "Login required";
        profileNameInput.value = "";
        profilePhoneInput.value = "";
        return;
    }

    profileDisplayName.textContent = profile.name;
    profileDisplayEmail.textContent = profile.email;
    profileNameInput.value = profile.name || "";
    profilePhoneInput.value = profile.phone || "";
}

function renderAdminAnalytics(analytics) {
    const safe = analytics || {
        totalUsers: 0,
        totalAdmins: 0,
        totalInvestors: 0,
        totalRegularUsers: 0,
        approvedPlots: 0,
        pendingPlots: 0,
        rejectedPlots: 0,
        reservedPlots: 0,
        totalBookings: 0,
        totalPayments: 0,
        successfulPayments: 0,
        failedPayments: 0
    };

    metricTotalUsers.textContent = String(safe.totalUsers);
    metricAdmins.textContent = String(safe.totalAdmins);
    metricInvestors.textContent = String(safe.totalInvestors);
    metricRegularUsers.textContent = String(safe.totalRegularUsers);
    metricApprovedPlots.textContent = String(safe.approvedPlots);
    metricPendingPlots.textContent = String(safe.pendingPlots);
    metricRejectedPlots.textContent = String(safe.rejectedPlots);
    metricReservedPlots.textContent = String(safe.reservedPlots);
    metricBookings.textContent = String(safe.totalBookings);
    metricPayments.textContent = String(safe.totalPayments);
    metricPaymentSuccess.textContent = String(safe.successfulPayments);
    metricPaymentFailed.textContent = String(safe.failedPayments);
}

function startEditingPlot(plot) {
    if (!isAdmin()) {
        setFeedback(plotFeedback, "Only ADMIN accounts can edit plots.", "error");
        return;
    }

    editingPlotIdInput.value = plot.plotId;
    document.getElementById("plotNumber").value = plot.plotNumber;
    document.getElementById("ownerName").value = plot.ownerName;
    document.getElementById("plotLocation").value = plot.location;
    document.getElementById("areaSqft").value = plot.areaSqft;
    document.getElementById("plotPrice").value = plot.price;
    document.getElementById("plotStatus").value = plot.status;
    document.getElementById("plotImageUrl").value = plot.imageUrl || "";

    plotFormTitle.textContent = `Editing ${plot.plotNumber}`;
    plotSubmitButton.textContent = "Save Changes";
    cancelEditButton.classList.remove("hidden");
    setFeedback(plotFeedback, `Editing plot ${plot.plotNumber}.`, "");
    plotForm.scrollIntoView({ behavior: "smooth", block: "center" });
}

async function deletePlot(plot) {
    if (!isAdmin()) {
        setFeedback(plotFeedback, "Only ADMIN accounts can delete plots.", "error");
        return;
    }

    const confirmed = window.confirm(`Delete plot ${plot.plotNumber}? This cannot be undone.`);
    if (!confirmed) {
        return;
    }

    try {
        await request(`/plots/${plot.plotId}`, {
            method: "DELETE"
        });
        lastAction.textContent = `Deleted plot ${plot.plotNumber}`;
        directoryLastAction.textContent = lastAction.textContent;
        if (editingPlotIdInput.value === String(plot.plotId)) {
            resetPlotForm();
        }
        await loadPlots();
        await loadAdminAnalytics();
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = "Plot delete failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
}

async function approvePlot(plot) {
    try {
        const updated = await request(`/plots/${plot.plotId}/approve`, {
            method: "POST"
        });
        lastAction.textContent = `Approved plot ${updated.plotNumber}`;
        directoryLastAction.textContent = lastAction.textContent;
        await loadPlots();
        await loadAdminAnalytics();
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = "Plot approval failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
}

async function rejectPlot(plot) {
    try {
        const updated = await request(`/plots/${plot.plotId}/reject`, {
            method: "POST"
        });
        lastAction.textContent = `Rejected plot ${updated.plotNumber}`;
        directoryLastAction.textContent = lastAction.textContent;
        await loadPlots();
        await loadAdminAnalytics();
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = "Plot rejection failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
}

async function bookPlot(plot) {
    if (isAdmin()) {
        setFeedback(plotFeedback, "ADMIN accounts cannot book plots.", "error");
        return;
    }

    openPaymentModal(plot);
}

async function toggleFavorite(plot) {
    if (isAdmin()) {
        return;
    }

    const isFavorite = state.favoritePlotIds.includes(plot.plotId);

    try {
        const response = await request(`/plots/${plot.plotId}/favorite`, {
            method: isFavorite ? "DELETE" : "POST"
        });
        lastAction.textContent = response.message;
        directoryLastAction.textContent = response.message;
        await loadFavorites();
        await loadPlots();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
    }
}

async function markNotificationRead(notificationId) {
    try {
        await request(`/notifications/${notificationId}/read`, {
            method: "POST"
        });
        lastAction.textContent = "Notification marked as read";
        directoryLastAction.textContent = "Notification marked as read";
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
    }
}

async function uploadSelectedImage() {
    if (!isAdmin()) {
        setFeedback(plotFeedback, "Only ADMIN accounts can upload plot images.", "error");
        return;
    }

    const file = plotImageFileInput.files[0];
    if (!file) {
        setFeedback(plotFeedback, "Choose an image file first.", "error");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);
    setFeedback(plotFeedback, "Uploading image...", "");

    try {
        const response = await request("/uploads/images", {
            method: "POST",
            body: formData
        });

        document.getElementById("plotImageUrl").value = response.imageUrl;
        setFeedback(plotFeedback, "Image uploaded successfully.", "success");
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
    }
}

async function cancelBooking(plot) {
    const confirmed = window.confirm(`Cancel booking for plot ${plot.plotNumber}?`);
    if (!confirmed) {
        return;
    }

    try {
        const updated = await request(`/plots/${plot.plotId}/cancel-booking`, {
            method: "POST"
        });
        lastAction.textContent = `Cancelled booking for ${updated.plotNumber}`;
        directoryLastAction.textContent = lastAction.textContent;
        await loadPlots();
        await loadMyBookings();
        await loadNotifications();
    } catch (error) {
        setFeedback(plotFeedback, error.message, "error");
        lastAction.textContent = "Booking cancellation failed";
        directoryLastAction.textContent = lastAction.textContent;
    }
}

async function request(url, options = {}) {
    const headers = new Headers(options.headers || {});
    if (state.token) {
        headers.set("Authorization", `Bearer ${state.token}`);
    }

    const response = await fetch(url, {
        ...options,
        headers
    });

    if (!response.ok) {
        let message = "Something went wrong.";

        try {
            const errorBody = await response.json();
            message = formatError(errorBody);
        } catch (error) {
            message = `Request failed with status ${response.status}.`;
        }

        throw new Error(message);
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

async function requestBlob(url, options = {}) {
    const headers = new Headers(options.headers || {});
    if (state.token) {
        headers.set("Authorization", `Bearer ${state.token}`);
    }

    const response = await fetch(url, {
        ...options,
        headers
    });

    if (!response.ok) {
        throw new Error(`Request failed with status ${response.status}.`);
    }

    return response.blob();
}

function formatError(errorBody) {
    if (errorBody.message) {
        return errorBody.message;
    }

    const messages = Object.values(errorBody);
    return messages.length > 0 ? messages[0] : "Something went wrong.";
}

function setFeedback(element, message, type) {
    element.textContent = message;
    element.className = "feedback";

    if (type) {
        element.classList.add(type);
    }
}

function clearFeedback() {
    setFeedback(signupFeedback, "", "");
    setFeedback(loginFeedback, "", "");
    setFeedback(otpFeedback, "", "");
    setFeedback(resetPasswordFeedback, "", "");
}

function handleAuthSuccess(response) {
    state.token = response.token;
    state.currentUser = response.user;
    localStorage.setItem("smartplot_token", response.token);
    localStorage.setItem("smartplot_user", JSON.stringify(response.user));
    currentUser.textContent = `${response.user.name} (${response.user.role})`;
    directoryCurrentUser.textContent = `${response.user.name} (${response.user.role})`;
    logoutButton.classList.remove("hidden");
    topbarLogoutButton.classList.remove("hidden");
    applyRoleView();
    window.scrollTo({ top: 0, behavior: "smooth" });
}

function resetProtectedViews(message) {
    state.totalUsers = 0;
    state.totalPlots = 0;
    state.myBookings = 0;
    state.totalPayments = 0;
    state.totalNotifications = 0;
    state.analytics = null;
    state.profile = null;
    state.favoritePlotIds = [];
    plotsGrid.innerHTML = "";
    myBookingsGrid.innerHTML = "";
    paymentsGrid.innerHTML = "";
    favoritesGrid.innerHTML = "";
    notificationsGrid.innerHTML = "";
    plotsEmpty.hidden = false;
    myBookingsEmpty.hidden = false;
    paymentsEmpty.hidden = false;
    favoritesEmpty.hidden = false;
    notificationsEmpty.hidden = false;
    plotsEmpty.querySelector("h3").textContent = "Login required";
    plotsEmpty.querySelector("p").textContent = message;
    myBookingsEmpty.querySelector("h3").textContent = "Login required";
    myBookingsEmpty.querySelector("p").textContent = message;
    paymentsEmpty.querySelector("h3").textContent = "Login required";
    paymentsEmpty.querySelector("p").textContent = message;
    favoritesEmpty.querySelector("h3").textContent = "Login required";
    favoritesEmpty.querySelector("p").textContent = message;
    notificationsEmpty.querySelector("h3").textContent = "Login required";
    notificationsEmpty.querySelector("p").textContent = message;
    renderProfile(null);
    renderAdminAnalytics(null);
    updatePortalPulse();
    applyRoleView();
}

async function loadProtectedData() {
    applyRoleView();
    await loadProfile();
    await loadUsers();
    await loadFavorites();
    await loadPlots();
    await loadMyBookings();
    await loadPayments();
    await loadNotifications();
    await loadAdminAnalytics();
}

function resetPlotForm() {
    plotForm.reset();
    document.getElementById("plotStatus").value = "AVAILABLE";
    document.getElementById("plotImageUrl").value = "";
    plotImageFileInput.value = "";
    editingPlotIdInput.value = "";
    plotFormTitle.textContent = "Publish and manage plot listings";
    plotSubmitButton.textContent = "Create Plot";
    cancelEditButton.classList.add("hidden");
    setFeedback(plotFeedback, "", "");
}

function updatePortalPulse() {
    const totalRecords = state.totalPlots + state.myBookings + state.favoritePlotIds.length;
    userCount.textContent = String(totalRecords);
    plotInventoryCount.textContent = String(state.totalPlots);
    plotCount.textContent = `${state.totalPlots} plots`;
    myBookingCount.textContent = String(state.myBookings);
    paymentCount.textContent = String(state.totalPayments);
    favoriteCount.textContent = String(state.favoritePlotIds.length);
    notificationCount.textContent = String(state.totalNotifications);
    const fill = Math.min(100, Math.max(8, totalRecords * 10));
    pulseFill.style.width = `${fill}%`;

    if (totalRecords === 0) {
        panelNote.textContent = "Fresh listings and activity will appear here as you explore.";
        return;
    }

    panelNote.textContent = `${state.totalPlots} live plot${state.totalPlots === 1 ? "" : "s"}, ${state.myBookings} booking${state.myBookings === 1 ? "" : "s"}, and ${state.favoritePlotIds.length} saved favorite${state.favoritePlotIds.length === 1 ? "" : "s"} in your journey.`;
}

function renderPlotMedia(plot) {
    if (plot.imageUrl) {
        return `<img class="plot-image" src="${escapeHtml(plot.imageUrl)}" alt="${escapeHtml(plot.plotNumber)}">`;
    }

    const initials = escapeHtml(plot.plotNumber.replace(/[^A-Za-z0-9]/g, "").slice(0, 4) || "PLOT");
    return `<div class="plot-image-fallback">${initials}</div>`;
}

function openPlotDetails(plot) {
    state.selectedPlot = plot;
    const statusClass = plot.status.toLowerCase();
    const approvalStatus = plot.approvalStatus || "PENDING";
    const approvalClass = approvalStatus.toLowerCase();

    plotDetailsTitle.textContent = `${plot.plotNumber} Overview`;
    plotDetailsStatus.className = `plot-status ${approvalClass}`;
    plotDetailsStatus.textContent = approvalStatus;
    plotDetailsPrice.textContent = formatCurrency(plot.price);
    plotDetailsLocation.textContent = plot.location;
    detailPlotNumber.textContent = plot.plotNumber;
    detailArea.textContent = `${plot.areaSqft} sqft`;
    detailOwner.textContent = plot.ownerName;
    detailBookedBy.textContent = plot.bookedByName || "Available";
    detailApprovalStatus.textContent = approvalStatus;
    plotDetailsSummary.textContent = `${plot.plotNumber} is located at ${plot.location} with a plotted area of ${plot.areaSqft} sqft, a listed price of ${formatCurrency(plot.price)}, and an approval state of ${approvalStatus}.`;
    plotDetailsInvestorNote.textContent = buildInvestorNote(plot);
    plotDetailsMedia.innerHTML = renderPlotMedia(plot);

    modalBookButton.classList.toggle("hidden", !(plot.status === "AVAILABLE" && approvalStatus === "APPROVED" && !isAdmin()));
    modalFavoriteButton.classList.toggle("hidden", isAdmin() || approvalStatus !== "APPROVED");
    modalEditButton.classList.toggle("hidden", !isAdmin());
    modalDeleteButton.classList.toggle("hidden", !isAdmin());

    modalBookButton.onclick = async () => {
        openPaymentModal(plot);
    };
    modalFavoriteButton.textContent = state.favoritePlotIds.includes(plot.plotId) ? "Remove Saved Plot" : "Save Plot";
    modalFavoriteButton.onclick = async () => {
        await toggleFavorite(plot);
        openPlotDetails(plot);
    };
    modalEditButton.onclick = () => {
        closePlotDetails();
        startEditingPlot(plot);
    };
    modalDeleteButton.onclick = async () => {
        await deletePlot(plot);
        closePlotDetails();
    };

    plotDetailsModal.classList.remove("hidden");
    plotDetailsModal.setAttribute("aria-hidden", "false");
    document.body.classList.add("modal-open");
}

function closePlotDetails() {
    state.selectedPlot = null;
    plotDetailsModal.classList.add("hidden");
    plotDetailsModal.setAttribute("aria-hidden", "true");
    if (paymentModal.classList.contains("hidden")) {
        document.body.classList.remove("modal-open");
    }
}

function openPaymentModal(plot) {
    state.paymentPlot = plot;
    paymentMethod.value = "UPI";
    paymentForm.querySelector('input[name="paymentOutcome"][value="success"]').checked = true;
    paymentAmount.textContent = formatCurrency(plot.price);
    paymentPlotSummary.textContent = `${plot.plotNumber} in ${plot.location} for ${formatCurrency(plot.price)}. A successful payment will reserve this plot under your account.`;
    setFeedback(paymentFeedback, "", "");
    paymentModal.classList.remove("hidden");
    paymentModal.setAttribute("aria-hidden", "false");
    document.body.classList.add("modal-open");
}

function closePaymentModal() {
    state.paymentPlot = null;
    paymentModal.classList.add("hidden");
    paymentModal.setAttribute("aria-hidden", "true");
    setFeedback(paymentFeedback, "", "");
    if (!plotDetailsModal.classList.contains("hidden")) {
        return;
    }
    document.body.classList.remove("modal-open");
}

function buildInvestorNote(plot) {
    const approvalStatus = plot.approvalStatus || "PENDING";

    if (approvalStatus === "PENDING") {
        return `This listing is currently pending admin approval, so it is not yet visible for buyer booking in the live catalogue.`;
    }

    if (approvalStatus === "REJECTED") {
        return `This listing has been rejected from the public catalogue and will need admin changes before it can move forward again.`;
    }

    if (plot.status === "AVAILABLE") {
        return `This listing is currently available and ready for reservation, with clear pricing visibility and live status tracking for decision-making.`;
    }

    if (plot.status === "RESERVED") {
        return `This listing is currently reserved, which signals active demand and gives a useful benchmark for comparable nearby inventory.`;
    }

    return `This listing has already moved to sold status, which helps indicate portfolio movement and recent market closure within the project.`;
}

function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\"", "&quot;")
        .replaceAll("'", "&#39;");
}

function formatCurrency(value) {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        maximumFractionDigits: 0
    }).format(Number(value));
}

function formatCompactCurrency(value) {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        notation: "compact",
        maximumFractionDigits: 1
    }).format(Number(value));
}

function isAdmin() {
    return state.currentUser && state.currentUser.role === "ADMIN";
}

function applyRoleView() {
    if (!state.currentUser) {
        accessSection.classList.remove("hidden");
        publicSections.forEach((section) => section.classList.remove("hidden"));
        rolesSection.classList.remove("hidden");
        protectedSections.forEach((section) => section.classList.add("hidden"));
        plotForm.classList.add("active");
        plotForm.style.display = "grid";
        buyerRoleLabel.textContent = "Home Buyer / Investor";
        pendingApprovalCount.textContent = "0";
        return;
    }

    accessSection.classList.add("hidden");
    publicSections.forEach((section) => section.classList.add("hidden"));
    rolesSection.classList.add("hidden");
    protectedSections.forEach((section) => section.classList.remove("hidden"));

    if (isAdmin()) {
        dashboardWelcomeCopy.textContent = "You are signed in as the SmartPlot team. Review listings, approvals, and uploads from one clean place.";
        adminDashboard.classList.remove("hidden");
        buyerDashboard.classList.add("hidden");
        paymentsEmpty.querySelector("h3").textContent = "Buyer-only section";
        paymentsEmpty.querySelector("p").textContent = "Payment activity appears here for home buyers and investors.";
        plotForm.classList.add("active");
        plotForm.style.display = "grid";
        plotFormTitle.textContent = "Publish and manage plot listings";
    } else {
        dashboardWelcomeCopy.textContent = "Welcome back. Browse approved plots, compare details, save your favorites, and reserve the right property with confidence.";
        adminDashboard.classList.add("hidden");
        buyerDashboard.classList.remove("hidden");
        buyerRoleLabel.textContent = state.currentUser.role;
        plotForm.style.display = "none";
        plotFormTitle.textContent = "Publish and manage plot listings";
        resetPlotForm();
    }
}

const storedUser = localStorage.getItem("smartplot_user");
if (storedUser) {
    try {
        state.currentUser = JSON.parse(storedUser);
        currentUser.textContent = `${state.currentUser.name} (${state.currentUser.role})`;
        directoryCurrentUser.textContent = `${state.currentUser.name} (${state.currentUser.role})`;
        if (state.token) {
            logoutButton.classList.remove("hidden");
            topbarLogoutButton.classList.remove("hidden");
        }
    } catch (error) {
        localStorage.removeItem("smartplot_user");
    }
}

if (state.token) {
    loadProtectedData();
} else {
    resetProtectedViews("Sign in to explore listings, favorites, and your booking activity.");
}
