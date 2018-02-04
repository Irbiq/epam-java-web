
package main.com.bsu.musicshop.command;


import main.com.bsu.musicshop.command.impl.GetAlbumCommand;
import main.com.bsu.musicshop.command.impl.LoginCommand;
import main.com.bsu.musicshop.command.impl.LogoutCommand;
import main.com.bsu.musicshop.command.impl.RegistrationCommand;

public enum CommandFactory {
    LOGIN {
        public AbstractCommand createCommand() {
            return new LoginCommand();
        }
    },
    LOGOUT {
        public AbstractCommand createCommand() {
            return new LogoutCommand();
        }
    },
    REGISTRATION{
        public AbstractCommand createCommand() {
            return new RegistrationCommand();
        }
    },
    GET_ALBUM {
        public AbstractCommand createCommand() {
            return new GetAlbumCommand();
        }
    },
    TO_ALBUMS {
        public AbstractCommand createCommand() {
            return new GetAlbumCommand();
        }
    };



    public abstract AbstractCommand createCommand();

}
