package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioController {

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * FROM tbusuario " + "WHERE email = ? AND senha = ? " + "and ativo = true";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            comando = gerenciador.prepararConexao(sql);

            comando.setString(1, email);
            comando.setString(2, senha);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return true;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando, resultado);
            
        }
        return false;
    }
    public boolean inserirUsuario(Usuario usu){
        String sql = "INSERT INTO tbusuario(nome, email, senha, datanasc, ativo) " + "VALUES (?,?,?,?,?)";
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        PreparedStatement comando = null;
        try {
            comando = gerenciador.prepararConexao(sql);
            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4, new java.sql.Date(usu.getDataNasc().getTime()));
            comando.setBoolean(5, usu.getAtivo());
            comando.executeUpdate();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            gerenciador.fecharConexao();
        }
        return false;
    }
    public boolean alterarUsuario(Usuario usu){
         String sql = "UPDATE tbusuario "+ "SET nome = ?, email = ?, senha = ?, datanasc = ?, ativo = ? " + "WHERE pkusuario = ?";
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        PreparedStatement comando = null;
        
        try {
            comando = gerenciador.prepararConexao(sql);
            comando.setInt(1, usu.getPkUsuario());
            comando.setString(2, usu.getNome());
            comando.setString(3, usu.getEmail());
            comando.setString(4, usu.getSenha());
            comando.setDate(5, new java.sql.Date(usu.getDataNasc().getTime()));
            comando.setBoolean(6, usu.getAtivo());
            comando.executeUpdate();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            gerenciador.fecharConexao();
        }
        return false;
    }
}
