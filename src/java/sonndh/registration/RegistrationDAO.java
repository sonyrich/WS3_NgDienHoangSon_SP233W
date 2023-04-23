package sonndh.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonndh.database.DBUtils;

public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select username From Registration "
                        + "Where username = ? And password = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchByLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From Registration "
                        + "Where lastname Like ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, lastname, role);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<RegistrationDTO>();
                    }

                    this.listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean insertRecord(String username, String password, String lastname, boolean role)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert Into Registration "
                        + "(username, password, lastname, isAdmin) "
                        + "values(?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteRecord(String pk)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Delete Registration "
                        + "Where username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, pk);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String username, String password, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "set password = ?, isAdmin = ? "
                        + "Where username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
