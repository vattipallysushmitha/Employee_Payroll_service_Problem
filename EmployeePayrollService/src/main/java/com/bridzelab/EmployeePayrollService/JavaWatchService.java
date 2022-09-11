package com.bridzelab.EmployeePayrollService;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class JavaWatchService {
    private static final Kind<?> ENTRY_DELETE = StandardWatchEventKinds.ENTRY_DELETE;
    private static final Kind<?> ENTRY_MODIFY = StandardWatchEventKinds.ENTRY_MODIFY;
    private static final Kind<?> ENTRY_CREATE = StandardWatchEventKinds.ENTRY_CREATE;
    private final WatchService watcher;
    private final Map<WatchKey, Path> dirWatchers;

    // Creates a WatchService and registers the given directory
    JavaWatchService(Path dir) throws IOException
    {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    // Register the given directory with the WatchService
    private void registerDirWatchers(Path dir) throws IOException
    {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE,ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }

    // Register the given directory, adn all its sub-directories, with the
    // WatchService
    private void scanAndRegisterDirectories(final Path start) throws IOException
    {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    // Process all events for keys queued to the watcher

    void processEvents()
    {
        while (true)
        {
            WatchKey key; // wait for key to be signalled
            try
            {
                key = watcher.take();
            }
            catch (InterruptedException x)
            {
                return;
            }
            Path dir = dirWatchers.get(key);
            if (dir == null)
                continue;
            for (WatchEvent<?> event : key.pollEvents())
            {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child); // print out event

                // if directory is created , then register it and its sub-dierctories
                if (kind == ENTRY_CREATE)
                {
                    try
                    {
                        if (Files.isDirectory(child))
                            scanAndRegisterDirectories(child);
                    }
                    catch (IOException e)
                    {
                    }
                }
                else if (kind.equals(ENTRY_DELETE))
                {
                    if (Files.isDirectory(child))
                        dirWatchers.remove(key);
                }
            }
            // reset key and remove from set if directory no longer accesible
            boolean valid = key.reset();
            if (!valid) {
                dirWatchers.remove(key);
                if (dirWatchers.isEmpty())
                    break; // all directories are inaccessible
            }
        }
    }

}
