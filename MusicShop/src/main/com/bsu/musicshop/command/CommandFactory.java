
package main.com.bsu.musicshop.command;


import main.com.bsu.musicshop.command.impl.*;
import main.com.bsu.musicshop.command.impl.admin.*;
import main.com.bsu.musicshop.command.impl.cart.AddToCartCommand;
import main.com.bsu.musicshop.command.impl.cart.AddToCartFromAlbumCommand;
import main.com.bsu.musicshop.command.impl.cart.DeleteFromCartCommand;

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
    REGISTRATION {
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
    LOCALE {
        public AbstractCommand createCommand() {
            return new LocaleCommand();
        }
    },
    ADD_TO_CART_FROM_ALBUM {
        public AbstractCommand createCommand() {
            return new AddToCartFromAlbumCommand();
        }
    },
    ADD_TO_CART {
        public AbstractCommand createCommand() {
            return new AddToCartCommand();
        }
    },
    DELETE_ALBUM {
        public AbstractCommand createCommand() {
            return new DeleteAlbumCommand();
        }
    },
    DELETE_AUDIO {
        public AbstractCommand createCommand() {
            return new DeleteAudioCommand();
        }
    },
    USER_DELETE {
        public AbstractCommand createCommand() {
            return new DeleteUserCommand();
        }
    },
    ADD_ALBUM{
        public AbstractCommand createCommand() {
            return new AddAlbumCommand();
        }
    },
    ADD_AUDIO{
        public AbstractCommand createCommand() {
            return new AddAudioCommand();
        }
    },
    ADD_AUDIO_TO_ALBUM{
        public AbstractCommand createCommand() {
            return new AddAudioToAlbumCommand();
        }
    },
    DELETE_FROM_CART{
        public AbstractCommand createCommand() {
            return new DeleteFromCartCommand();
        }
    },
    USER_EDIT{
        public AbstractCommand createCommand() {
            return new GetEditUserCommand();
        }
    },
    BUY_ALL{
        public AbstractCommand createCommand() {
            return new BuyAllCommand();
        }
    },
    EDIT_USER_CONFIRM{
        public AbstractCommand createCommand() {
            return new EditUserConfirmCommand();
        }
    };



    public abstract AbstractCommand createCommand();

}
