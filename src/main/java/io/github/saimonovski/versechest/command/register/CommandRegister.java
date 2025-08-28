package io.github.saimonovski.versechest.command.register;

import io.github.saimonovski.versechest.VerseChest;
import io.github.saimonovski.versechest.util.Language;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.minecraft.extras.MinecraftHelp;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

import java.util.Collection;
import java.util.Set;

public class CommandRegister {
    private final VerseChest plugin;
    private final Language language;
    public CommandRegister(VerseChest plugin, Language language) {
        this.plugin = plugin;
        this.language = language;
        final LegacyPaperCommandManager<CommandSender> manager = new LegacyPaperCommandManager<>(
                plugin,
                ExecutionCoordinator.simpleCoordinator(),
                SenderMapper.identity()
        );
        manager.captionRegistry().registerProvider(MinecraftHelp.defaultCaptionsProvider());

        switch (this.language){
            case POLISH: CaptionRegister.registerPolish(manager);
            case ENGLISH: CaptionRegister.registerEnglish(manager);
            default: CaptionRegister.registerEnglish(manager);
        }

        AnnotationParser<CommandSender> annotationParser = new AnnotationParser<>(manager, CommandSender.class);
        parseCommands(annotationParser);
    }
    private Collection<Object> polishCommands(){
        return Set.of(); //todo insert commands here
    }
    private Collection<Object> englishCommands(){
        return Set.of(); //todo insert commands here
    }
    private  void parseCommands(AnnotationParser<CommandSender> annotationParser) {
        annotationParser.parse(
                switch (this.language) {
                    case POLISH -> polishCommands();
                    case ENGLISH -> englishCommands();
                }
        );
    }
}
