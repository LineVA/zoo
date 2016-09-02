package commandLine;


/**
 *
 * @author doyenm
 */
//public class CommandManager {
//
//    Play play;
//    private final Iterable<Command> playCommands;
//    private final Iterable<Command> initialCommands;
//
//    public boolean isInitiate = false;
//
//    public CommandManager(Play play) {
//        this.play = play;
//        // For Paddock and Animal : Ls must be before Detail
//        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
//                new CreatePaddock(play),
//                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
//                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
//                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
//                new FeedingAnimal(play),
//                new RemoveAnimal(play), new RemovePaddock(play),
//                new LsSpecie(play), new DetailSpecie(play),
//                new LsFeeding(play),
//                new SaveZoo(play), new LoadZoo(play),
//                new Options(play));
//        initialCommands = asList(new CreateZoo(play), new LoadZoo(play), new Options(play));
//    }
//
//    public String run(String cmd) {
//        // String[] parse = cmd.split(" ");
//        String[] parse = SplitDoubleQuotes.split(cmd);
//        if (isInitiate) {
//            for (Command command : playCommands) {
//                if (command.canExecute(parse)) {
//                    String result = command.execute(parse);
//                    this.isInitiate |= command.hasInitiateAZoo();
//                    return result;
//                }
//            }
//            return this.play.getBundle().getString("UNKNOWN_CMD");
//        } else {
//            for (Command command : initialCommands) {
//                if (command.canExecute(parse)) {
//                    String result = command.execute(parse);
//                    this.isInitiate = command.hasInitiateAZoo();
//                    return result;
//                }
//            }
//            return this.play.getBundle().getString("MUST_CREATE_ZOO");
//        }
public interface CommandManager {

    public String run(String cmd);

    public String getFirstLine();
}
