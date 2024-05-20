package ma.zs.zyn.zynerator.security.service.facade;

import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.zyn.zynerator.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User, UserCriteria>, UserDetailsService {

    User findByUsername(String username);

    User findByUsernameWithRoles(String username);

    String cryptPassword(String value);

    boolean changePassword(String username, String newPassword);

    int deleteByUsername(String username);

    UserDetails loadUserByUsername(String username);
    public String generateCode(int length);
    public User create(User t);

    User findByCode(String code);
}
