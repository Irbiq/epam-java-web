
package main.com.bsu.musicshop.command;


import main.com.bsu.musicshop.command.impl.*;

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
    ADD_COMMENT {
        public AbstractCommand createCommand() {
            return new AddCommentCommand();
        }
    },
    TO_ALBUMS {
        public AbstractCommand createCommand() {
            return new GetAlbumCommand();
        }
    },
    LOCALE{
        public AbstractCommand createCommand() {
            return new LocaleCommand();
        }
    };



    public abstract AbstractCommand createCommand();

}
