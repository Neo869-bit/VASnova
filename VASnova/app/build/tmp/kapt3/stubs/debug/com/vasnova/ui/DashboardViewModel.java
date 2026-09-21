package com.vasnova.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/vasnova/ui/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/vasnova/domain/IoTRepository;", "(Lcom/vasnova/domain/IoTRepository;)V", "_powerEnabled", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/vasnova/domain/ConnectionState;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "powerEnabled", "getPowerEnabled", "telemetry", "Lcom/vasnova/domain/DeviceTelemetry;", "getTelemetry", "connect", "", "disconnect", "togglePower", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.vasnova.domain.IoTRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.ConnectionState> connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.DeviceTelemetry> telemetry = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _powerEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> powerEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String FAKE_DEVICE_ADDRESS = "fake-esp32";
    @org.jetbrains.annotations.NotNull()
    public static final com.vasnova.ui.DashboardViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.vasnova.domain.IoTRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.DeviceTelemetry> getTelemetry() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getPowerEnabled() {
        return null;
    }
    
    public final void connect() {
    }
    
    public final void disconnect() {
    }
    
    public final void togglePower() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/vasnova/ui/DashboardViewModel$Companion;", "", "()V", "FAKE_DEVICE_ADDRESS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}