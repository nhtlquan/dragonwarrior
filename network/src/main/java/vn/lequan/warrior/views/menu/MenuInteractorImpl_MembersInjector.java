package vn.lequan.warrior.views.menu;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.views.play.PlayView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MenuInteractorImpl_MembersInjector
    implements MembersInjector<MenuInteractorImpl> {
  private final Provider<PlayView> playViewProvider;

  public MenuInteractorImpl_MembersInjector(Provider<PlayView> playViewProvider) {
    assert playViewProvider != null;
    this.playViewProvider = playViewProvider;
  }

  public static MembersInjector<MenuInteractorImpl> create(Provider<PlayView> playViewProvider) {
    return new MenuInteractorImpl_MembersInjector(playViewProvider);
  }

  @Override
  public void injectMembers(MenuInteractorImpl instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.playView = playViewProvider.get();
  }

  public static void injectPlayView(
      MenuInteractorImpl instance, Provider<PlayView> playViewProvider) {
    instance.playView = playViewProvider.get();
  }
}
