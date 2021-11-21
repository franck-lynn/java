package base.general.rpc.rpc03;

import base.general.rpc.user.User;
import base.general.rpc.user.IUserService;
public class UserServiceImpl implements IUserService{

    @Override
    public User findUserById(Integer id) {
        return new User(id, "Alice");
    }

}
