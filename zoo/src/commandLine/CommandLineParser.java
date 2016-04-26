package commandLine;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class CommandLineParser {

    @Getter
    private Transmission transmission;

    public CommandLineParser(Transmission transmission) {
        this.transmission = transmission;
    }

    public String hello() {
        return "Hello world";
    }

    private String animal(String[] parse) {
        return "";
    }

    private void findMan(String[] parse) {
        if (parse.length == 2) {
            switch (parse[1]) {
//                case "man":
//                    return manMan();
//                case "pad":
//                case "paddock":
//                    return manPaddock();
//                case "zoo":
//                    return manZoo();
                default:
                    unknownCmd(parse, "man");
            }
        } else {
            unknownCmd(parse, "man");
        }
    }

    public String man(String[] parse) {
        switch (parse.length) {
            case 1:
                return "See 'man man' for more information.";
            case 2:
                findMan(parse);
                return "";
            default:
                return "See 'man man' for more information.";
        }
    }

    private String manMan() {
        return "man man";
    }

    private String manPaddock() {
        return "man paddock";
    }

    private String manZoo() {
        return "man zoo";
    }

    private void paddock(String[] parse) {
        boolean found = false;
        switch (parse.length) {
            case 2:
                if (parse[1].equals("ls")) {
                    transmission.paddockLs();
                    found = true;
                } else {
                    transmission.paddockDetailed(parse[1]);
                    found = true;
                }
            case 7:
                if (parse[1].equals("create")) {
                    transmission.paddockCreate(parse[2], parse[3], parse[4],
                            parse[5], parse[6]);
                    found = true;

                }
        }
        if (!found) {
            unknownCmd(parse, "paddock");
        }
    }
//        if (this.zoo != null) {
//            switch (parse.length) {
//                case 2:
//                    if (parse[1].equals("ls")) {
//                        return this.zoo.listPaddock();
//                    } else {
//                        return zoo.detailedPaddock(parse[1]);
//                    }
//                case 7:
//                    if (parse[1].equals("create")) {
//                        try {
//                            this.zoo.addPaddock(parse[2],
//                                    this.stringToInteger(parse[3]),
//                                    this.stringToInteger(parse[4]),
//                                    this.stringToInteger(parse[5]),
//                                    this.stringToInteger(parse[6]));
//                            return "This paddock has been successfully created.";
//                        } catch (AlreadyUsedNameException ex) {
//                            return ex.getMessage();
//                        }
//                    } else {
//                        return unknownCmd(parse, "paddock");
//                    }
//                default:
//                    return unknownCmd(parse, "paddock");
//            }
//        } else {
//            return "You must create a zoo before.";
//        }
//        return null;
    //}

    public void unknownCmd(String[] parse, String cpt) {
        this.transmission.unknownCmd(cpt);
    }

    /**
     * Function to analyze the cmd lines beginning with zoo > zoo map to see the
     * map > zoo xyz to create a zoo called xyz
     *
     * @param parse
     * @return
     */
    private void zoo(String[] parse) {
        boolean found = false;
        // zoo map
        if (parse.length == 2) {
            if (parse[1].equals("map")) {
                //   return formatting.zooMap(zoo.getWidth(), zoo.getHeight());
                this.transmission.zooMap();
                found = true;
            }
            // zoo create name width height
        } else if (parse.length == 5) {
            if (parse[1].equals("create")) {
                this.transmission.zooCreate(parse[2], parse[3], parse[4]);
                found = true;
            }
        }
        if (!found) {
            unknownCmd(parse, "zoo");
        }
    }

    public String[] parse(String cmd) {
        return cmd.split(" ");
    }

    public void analyze(String[] parse) {
        if (parse.length != 0) {
            switch (parse[0]) {
//                case "man":
//                    return man(parse);
//                //      break;
                case "pad":
                case "paddock":
                    paddock(parse);
                    break;
                case "zoo":
                    zoo(parse);
                    break;
                default:
                    unknownCmd(parse, null);
                    break;
            }
        }
    }

    public void parseAnalyzeAndAct(String cmd) {
        String[] parse = this.parse(cmd);
        this.analyze(parse);
    }
}
