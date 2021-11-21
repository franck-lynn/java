package base.general.rpc.rpc04;

import base.general.rpc.user.IUserService;
import base.general.rpc.user.User;

public class UserServiceImpl implements IUserService{

    @Override
    public User findUserById(Integer id) {
        return new User(id, "Alice");
    }

}
