package com.vasnova.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0015H\u0002J$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\u0006\u0010\u001e\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\""}, d2 = {"Lcom/vasnova/data/FakeIoTRepository;", "Lcom/vasnova/domain/IoTRepository;", "()V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/vasnova/domain/ConnectionState;", "_telemetry", "Lcom/vasnova/domain/DeviceTelemetry;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "powerEnabled", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "telemetry", "getTelemetry", "telemetryJob", "Lkotlinx/coroutines/Job;", "connect", "", "deviceAddress", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startTelemetryEmission", "togglePower", "Lkotlin/Result;", "enabled", "togglePower-gIAlu-s", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class FakeIoTRepository implements com.vasnova.domain.IoTRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.vasnova.domain.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.ConnectionState> connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.vasnova.domain.DeviceTelemetry> _telemetry = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.DeviceTelemetry> telemetry = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job telemetryJob;
    private boolean powerEnabled = true;
    private static final long TELEMETRY_INTERVAL_MS = 1000L;
    private static final long CONNECT_DELAY_MS = 500L;
    private static final float BASE_TEMPERATURE = 24.5F;
    private static final float TEMPERATURE_VARIANCE = 1.0F;
    private static final int BASE_BATTERY = 85;
    private static final float BASE_INPUT_POWER = 45.0F;
    private static final float BASE_OUTPUT_POWER = 12.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.vasnova.data.FakeIoTRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public FakeIoTRepository() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.ConnectionState> getConnectionState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.vasnova.domain.DeviceTelemetry> getTelemetry() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object disconnect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void startTelemetryEmission() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/vasnova/data/FakeIoTRepository$Companion;", "", "()V", "BASE_BATTERY", "", "BASE_INPUT_POWER", "", "BASE_OUTPUT_POWER", "BASE_TEMPERATURE", "CONNECT_DELAY_MS", "", "TELEMETRY_INTERVAL_MS", "TEMPERATURE_VARIANCE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}