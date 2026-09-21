package com.vasnova.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001aL\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\b\u0010\r\u001a\u00020\u0001H\u0003\u001a\b\u0010\u000e\u001a\u00020\u0001H\u0003\u001a\u0012\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\"\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bH\u0003\u001a8\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0003\u00a8\u0006\u001f"}, d2 = {"ConnectionStatusBadge", "", "connectionState", "Lcom/vasnova/domain/ConnectionState;", "DashboardContent", "telemetry", "Lcom/vasnova/domain/DeviceTelemetry;", "powerEnabled", "", "onConnect", "Lkotlin/Function0;", "onDisconnect", "onTogglePower", "DashboardContentDisconnectedPreview", "DashboardContentPreview", "DashboardScreen", "viewModel", "Lcom/vasnova/ui/DashboardViewModel;", "HeaderRow", "MetricCard", "metric", "Lcom/vasnova/ui/MetricItem;", "modifier", "Landroidx/compose/ui/Modifier;", "isLive", "NeonSwitchButton", "label", "", "filled", "enabled", "onClick", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    com.vasnova.ui.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DashboardContent(com.vasnova.domain.ConnectionState connectionState, com.vasnova.domain.DeviceTelemetry telemetry, boolean powerEnabled, kotlin.jvm.functions.Function0<kotlin.Unit> onConnect, kotlin.jvm.functions.Function0<kotlin.Unit> onDisconnect, kotlin.jvm.functions.Function0<kotlin.Unit> onTogglePower) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HeaderRow(com.vasnova.domain.ConnectionState connectionState) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ConnectionStatusBadge(com.vasnova.domain.ConnectionState connectionState) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MetricCard(com.vasnova.ui.MetricItem metric, androidx.compose.ui.Modifier modifier, boolean isLive) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void NeonSwitchButton(java.lang.String label, boolean filled, boolean enabled, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true, backgroundColor = 4278914830L)
    @androidx.compose.runtime.Composable()
    private static final void DashboardContentPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true, backgroundColor = 4278914830L)
    @androidx.compose.runtime.Composable()
    private static final void DashboardContentDisconnectedPreview() {
    }
}