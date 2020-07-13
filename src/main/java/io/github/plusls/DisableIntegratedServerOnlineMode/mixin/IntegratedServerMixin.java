package io.github.plusls.DisableIntegratedServerOnlineMode.mixin;

import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    // modify Set OnlineMode
    @ModifyArg(method="setupServer()Z",
            at=@At(value="INVOKE", target="Lnet/minecraft/server/integrated/IntegratedServer;setOnlineMode(Z)V", ordinal=0), index=0)
    private boolean modifySetOnlineMode(boolean onlineMode){
        return false;
    }
}