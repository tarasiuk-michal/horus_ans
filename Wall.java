import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


interface Block {
    String getColor();

    String getMaterial();
}

interface CompositeBlock extends Block {
    List getBlocks();
}

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

public class Wall implements Structure {
    private List<CompositeBlock> blocks = new ArrayList<>();

    @Override
    public Optional findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findAny();
    }

    @Override
    public List findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return blocks.stream()
                .map(CompositeBlock::getBlocks)
                .mapToInt(List::size)
                .sum();
    }
}
