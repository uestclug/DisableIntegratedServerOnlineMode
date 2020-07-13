package io.github.plusls.DisableIntegratedServerOnlineMode.mixin;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import io.github.plusls.DisableIntegratedServerOnlineMode.util.MyProfileLookupCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandlerMixin extends Object {
    @Shadow
    private MinecraftServer server;
    @Inject(method = "toOfflineProfile(Lcom/mojang/authlib/GameProfile;)Lcom/mojang/authlib/GameProfile;",
            at = @At(value = "HEAD"), cancellable = true)
    private void onToOfflineProfile(GameProfile profile, CallbackInfoReturnable<GameProfile> info) {
        MyProfileLookupCallback myProfileLookupCallback = new MyProfileLookupCallback();
        String username = profile.getName();
        this.server.getGameProfileRepo().findProfilesByNames(new String[]{ username }, Agent.MINECRAFT, myProfileLookupCallback);
        if (myProfileLookupCallback.gameProfile == null) {
            System.out.println("get gameProfile fail");
            return;
        } else {
            System.out.println("get gameProfile success!");
            info.setReturnValue(myProfileLookupCallback.gameProfile);
            info.cancel();
        }
    }

}
