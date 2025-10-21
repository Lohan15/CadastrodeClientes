import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException; // Necessário para o MaskFormatter

public class FormularioClientes extends JFrame {

    // DECLARAÇÃO DOS COMPONENTES
    private JTextField campoNomeCompleto;
    private JTextField campoEndereco;
    private JTextField campoCidade;
    private JComboBox<String> comboEstado;
    private JFormattedTextField campoTelefone;
    private JRadioButton radioAtivo;
    private JRadioButton radioInativo;
    private ButtonGroup grupoStatus;

    private JButton botaoGravar;
    private JButton botaoCancelar;
    private JButton botaoVoltar;

    // Tamanho desejado para os ícones
    private static final int ICON_WIDTH = 24; 
    private static final int ICON_HEIGHT = 24; 
    private static final int VOLTAR_ICON_SIZE = 32; 

    public FormularioClientes() {
        // Configuração da Janela
        setTitle("Cadastrar Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // ------------------ 1. PAINEL SUPERIOR (Barra de Ferramentas - Ações) ------------------
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5)); 

        // Botão Gravar Dados
        ImageIcon iconGravarOriginal = new ImageIcon(getClass().getResource("/icons/gravar.png"));
        Image imgGravar = iconGravarOriginal.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH);
        botaoGravar = new JButton("Gravar Dados", new ImageIcon(imgGravar));
        botaoGravar.setVerticalTextPosition(SwingConstants.BOTTOM);
        botaoGravar.setHorizontalTextPosition(SwingConstants.CENTER);
        painelSuperior.add(botaoGravar);

        // Botão Cancelar Cadastro
        ImageIcon iconCancelarOriginal = new ImageIcon(getClass().getResource("/icons/cancelar.png"));
        Image imgCancelar = iconCancelarOriginal.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH);
        botaoCancelar = new JButton("Cancelar Cadastro", new ImageIcon(imgCancelar));
        botaoCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
        botaoCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        painelSuperior.add(botaoCancelar);

        add(painelSuperior, BorderLayout.NORTH);

        // ------------------ 2. PAINEL CENTRAL (Instrução e Formulário de Campos) ------------------
        JPanel painelCentral = new JPanel(new BorderLayout(0, 15)); 
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 

        // Área de mensagem/instrução
        JLabel labelInstrucao = new JLabel("Preencha os dados corretamente e clique em Gravar Dados");
        labelInstrucao.setFont(new Font("Arial", Font.BOLD, 14));
        labelInstrucao.setHorizontalAlignment(SwingConstants.CENTER); 
        painelCentral.add(labelInstrucao, BorderLayout.NORTH);

        // Painel dos Campos (GridBagLayout para centralizar os campos)
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // Inicialização dos Campos

        // Nome Completo
        campoNomeCompleto = new JTextField(25);
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0; 
        painelCampos.add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; 
        painelCampos.add(campoNomeCompleto, gbc);

        // Endereço
        campoEndereco = new JTextField(25);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(new JLabel("Endereço:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        painelCampos.add(campoEndereco, gbc);

        // Cidade
        campoCidade = new JTextField(25);
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painelCampos.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        painelCampos.add(campoCidade, gbc);

        // Estado (JComboBox)
        String[] estados = {"Selecione", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
        comboEstado = new JComboBox<>(estados);
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        painelCampos.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        painelCampos.add(comboEstado, gbc);

        // Telefone (JFormattedTextField) - SOLUÇÃO ROBUSTA APLICADA
        try {
            // Máscara para telefone no formato (XX) XXXXX-XXXX.
            MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####"); 
            mascaraTelefone.setPlaceholderCharacter('_'); 
            mascaraTelefone.setAllowsInvalid(false); 
            mascaraTelefone.setValueContainsLiteralCharacters(false); 
            
            campoTelefone = new JFormattedTextField(mascaraTelefone);
            campoTelefone.setColumns(25);
            
            // Listener para forçar o cursor para a posição 0 ao ganhar foco
            campoTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    SwingUtilities.invokeLater(() -> campoTelefone.setCaretPosition(0));
                }
            });

        } catch (ParseException e) {
            campoTelefone = new JFormattedTextField(25); 
            System.err.println("Erro ao aplicar máscara de telefone: " + e.getMessage());
        }

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painelCampos.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        painelCampos.add(campoTelefone, gbc);

        // Status (JRadioButton Group)
        radioAtivo = new JRadioButton("Ativo");
        radioInativo = new JRadioButton("Inativo");
        grupoStatus = new ButtonGroup();
        grupoStatus.add(radioAtivo);
        grupoStatus.add(radioInativo);
        radioAtivo.setSelected(true); 

        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelStatus.add(radioAtivo);
        painelStatus.add(radioInativo);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        painelCampos.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        painelCampos.add(painelStatus, gbc);
        
        // Espaçador para empurrar o formulário para cima (centralização vertical)
        gbc.gridx = 0; gbc.gridy = 6; gbc.weighty = 1.0; 
        painelCampos.add(Box.createVerticalGlue(), gbc); 

        painelCentral.add(painelCampos, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

        // ------------------ 3. PAINEL INFERIOR (Botão Voltar) ------------------
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5)); 

        ImageIcon iconVoltarOriginal = new ImageIcon(getClass().getResource("/icons/voltar.png"));
        Image imgVoltar = iconVoltarOriginal.getImage().getScaledInstance(VOLTAR_ICON_SIZE, VOLTAR_ICON_SIZE, Image.SCALE_SMOOTH);
        botaoVoltar = new JButton("Voltar", new ImageIcon(imgVoltar));
        botaoVoltar.setVerticalTextPosition(SwingConstants.CENTER);
        botaoVoltar.setHorizontalTextPosition(SwingConstants.RIGHT);
        painelInferior.add(botaoVoltar);

        add(painelInferior, BorderLayout.SOUTH);

        // ------------------ 4. LISTENERS E AÇÃO ------------------

        // Listener para o botão "Gravar Dados"
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarEProcessarDados();
            }
        });

        // Configuração final da Janela
        pack(); 
        setMinimumSize(new Dimension(500, 450)); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    /**
     * Método para validar se todos os campos obrigatórios estão preenchidos.
     */
    private void validarEProcessarDados() {
        List<String> camposFaltantes = new ArrayList<>();

        // 1. Nome Completo (Obrigatório)
        if (campoNomeCompleto.getText().trim().isEmpty()) {
            camposFaltantes.add("Nome Completo");
        }

        // 2. Endereço (Obrigatório)
        if (campoEndereco.getText().trim().isEmpty()) {
            camposFaltantes.add("Endereço");
        }

        // 3. Cidade (Obrigatório)
        if (campoCidade.getText().trim().isEmpty()) {
            camposFaltantes.add("Cidade");
        }

        // 4. Estado (Não pode ser a opção "Selecione")
        if (comboEstado.getSelectedItem().equals("Selecione")) {
             camposFaltantes.add("Estado");
        }

        // 5. Telefone (JFormattedTextField): Verifica se a máscara foi preenchida
        // Contagem de dígitos obrigatórios (10 ou 11 no total)
        if (campoTelefone.getText().replaceAll("[^0-9]", "").length() < 10) { 
             camposFaltantes.add("Telefone (Preenchimento incompleto)");
        }

        // Ação Final: Apresentar o resultado da validação
        if (camposFaltantes.isEmpty()) {
            // Sucesso
            JOptionPane.showMessageDialog(this,
                    "Dados do Cliente gravados com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Erro
            String mensagemErro = "Por favor, preencha o(s) campo(s) não preenchido(s):\n\n" +
                                  String.join("\n", camposFaltantes);

            JOptionPane.showMessageDialog(this,
                    mensagemErro,
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignora se não conseguir aplicar o Look and Feel
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioClientes();
            }
        });
    }
}
