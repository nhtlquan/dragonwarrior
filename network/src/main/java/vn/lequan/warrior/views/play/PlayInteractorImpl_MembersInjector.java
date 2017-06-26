package vn.lequan.warrior.views.play;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.views.menu.MenuView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PlayInteractorImpl_MembersInjector
    implements MembersInjector<PlayInteractorImpl> {
  private final Provider<MenuView> menuViewProvider;

  public PlayInteractorImpl_MembersInjector(Provider<MenuView> menuViewProvider) {
    assert menuViewProvider != null;
    this.menuViewProvider = menuViewProvider;
  }

  public static MembersInjector<PlayInteractorImpl> create(Provider<MenuView> menuViewProvider) {
    return new PlayInteractorImpl_MembersInjector(menuViewProvider);
  }

  @Override
  public void injectMembers(PlayInteractorImpl instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.menuView = menuViewProvider.get();
  }

  public static void injectMenuView(
      PlayInteractorImpl instance, Provider<MenuView> menuViewProvider) {
    instance.menuView = menuViewProvider.get();
  }
}
